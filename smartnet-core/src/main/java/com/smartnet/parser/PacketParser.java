
package com.smartnet.parser;

import com.smartnet.model.ParsedPacket;
import com.smartnet.model.Protocol;
import com.smartnet.model.RawPacket;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PacketParser {

    // Ethernet Header
    // 6 bytes Destination MAC
    // 6 bytes Source MAC
    // 2 bytes EtherType
    private static final int ETHERNET_HEADER_LENGTH = 14;
    private static final int MAC_ADDRESS_LENGTH = 6;

    // Minimum IPv4 header size
    private static final int IPV4_MIN_HEADER_LENGTH = 20;
    // Minimum header sizes
    private static final int TCP_MIN_HEADER_LENGTH = 20;
    private static final int UDP_HEADER_LENGTH = 8;

    /**
     * Parses a raw packet captured from the PCAP file.
     * Currently supports Ethernet and IPv4 headers.
     */
    public ParsedPacket parse(RawPacket rawPacket) {

        if (rawPacket == null || rawPacket.getData() == null) {
            return null;
        }

        byte[] data = rawPacket.getData();

        if (data.length < ETHERNET_HEADER_LENGTH) {
            return null;
        }

        ParsedPacket packet = new ParsedPacket();
        packet.setPacketLength(rawPacket.getHeader().getIncludedLength());
        packet.setTimestamp(rawPacket.getHeader().getTimestampSeconds());
        // Network protocols use Big Endian byte order.
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.order(ByteOrder.BIG_ENDIAN);

        if (!parseEthernet(buffer, packet)) {
            return null;
        }

        // Continue parsing only if the packet is IPv4.
        if (packet.getEtherType() == 0x0800) {

            if (!parseIPv4(buffer, packet)) {
                return null;
            }

            if (packet.getProtocol() == Protocol.TCP) {

                if (!parseTCP(buffer, packet)) {
                    return null;
                }

            } else if (packet.getProtocol() == Protocol.UDP) {

                if (!parseUDP(buffer, packet)) {
                    return null;
                }
            }
        }

        return packet;
    }

    /**
     * Reads the Ethernet header.
     */
    private boolean parseEthernet(ByteBuffer buffer, ParsedPacket packet) {

        if (buffer.remaining() < ETHERNET_HEADER_LENGTH) {
            return false;
        }

        byte[] destinationMac = new byte[MAC_ADDRESS_LENGTH];
        byte[] sourceMac = new byte[MAC_ADDRESS_LENGTH];

        buffer.get(destinationMac);
        buffer.get(sourceMac);

        int etherType = Short.toUnsignedInt(buffer.getShort());

        packet.setDestinationMac(macToString(destinationMac));
        packet.setSourceMac(macToString(sourceMac));
        packet.setEtherType(etherType);

        return true;
    }

    /**
     * Reads the IPv4 header and extracts the basic fields.
     */
    private boolean parseIPv4(ByteBuffer buffer, ParsedPacket packet) {

        if (buffer.remaining() < IPV4_MIN_HEADER_LENGTH) {
            return false;
        }

        // First byte contains Version (upper 4 bits)
        // and Header Length (lower 4 bits).
        int versionAndLength = Byte.toUnsignedInt(buffer.get());

        int version = versionAndLength >> 4;
        int headerLength = (versionAndLength & 0x0F) * 4;

        if (version != 4) {
            return false;
        }

        if (headerLength < IPV4_MIN_HEADER_LENGTH ||
                buffer.remaining() < headerLength - 1) {
            return false;
        }

        packet.setIpVersion(version);
        packet.setIpHeaderLength(headerLength);

        // Skip DSCP + ECN
        buffer.get();

        // Total Length (unused for now)
        buffer.getShort();

        // Skip Identification
        buffer.getShort();

        // Skip Flags + Fragment Offset
        buffer.getShort();

        // Read TTL
        packet.setTtl(Byte.toUnsignedInt(buffer.get()));

        // Read Protocol
        packet.setProtocol(Protocol.fromNumber(Byte.toUnsignedInt(buffer.get())));

        // Skip Header Checksum
        buffer.getShort();

        // Source IP
        byte[] sourceIp = new byte[4];
        buffer.get(sourceIp);
        packet.setSourceIp(ipToString(sourceIp));

        // Destination IP
        byte[] destinationIp = new byte[4];
        buffer.get(destinationIp);
        packet.setDestinationIp(ipToString(destinationIp));

        // Skip optional IPv4 header fields if present.
        int optionBytes = headerLength - IPV4_MIN_HEADER_LENGTH;

        if (optionBytes > 0) {
            if (buffer.remaining() < optionBytes) {
                return false;
            }

            buffer.position(buffer.position() + optionBytes);
        }

        return true;
    }

    /**
     * Converts a MAC address to the standard format.
     * Example: 00:1A:2B:3C:4D:5E
     */
    private String macToString(byte[] mac) {

        StringBuilder builder = new StringBuilder(17);

        for (int i = 0; i < mac.length; i++) {

            builder.append(String.format("%02X", mac[i]));

            if (i < mac.length - 1) {
                builder.append(":");
            }
        }

        return builder.toString();
    }

    /**
     * Converts a 4-byte IPv4 address into dotted decimal format.
     * Example: 192.168.1.10
     */
    private String ipToString(byte[] ip) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < ip.length; i++) {

            builder.append(Byte.toUnsignedInt(ip[i]));

            if (i < ip.length - 1) {
                builder.append(".");
            }
        }

        return builder.toString();
    }

    /**
     * Reads the TCP header and extracts the basic fields.
     */
    private boolean parseTCP(ByteBuffer buffer, ParsedPacket packet) {

        if (buffer.remaining() < TCP_MIN_HEADER_LENGTH) {
            return false;
        }

        // Read source and destination ports
        packet.setSourcePort(Short.toUnsignedInt(buffer.getShort()));
        packet.setDestinationPort(Short.toUnsignedInt(buffer.getShort()));

        // Skip Sequence Number
        buffer.getInt();

        // Skip Acknowledgement Number
        buffer.getInt();

        // Read Data Offset (Header Length)
        int dataOffset = (Byte.toUnsignedInt(buffer.get()) >> 4) * 4;
        packet.setTcpHeaderLength(dataOffset);

        // Skip Flags
        buffer.get();

        // Skip Window Size
        buffer.getShort();

        // Skip Checksum
        buffer.getShort();

        // Skip Urgent Pointer
        buffer.getShort();

        // Skip TCP Options (if any)
        int optionBytes = dataOffset - TCP_MIN_HEADER_LENGTH;

        if (optionBytes > 0) {

            if (buffer.remaining() < optionBytes) {
                return false;
            }

            buffer.position(buffer.position() + optionBytes);
        }

        // ==========================
        // Extract TCP Payload
        // ==========================

        int payloadLength = buffer.remaining();

        if (payloadLength > 0) {

            byte[] payload = new byte[payloadLength];
            buffer.get(payload);

            packet.setPayload(payload);

        } else {

            packet.setPayload(new byte[0]);
        }

        return true;
    }

    /**
     * Reads the UDP header.
     */
    private boolean parseUDP(ByteBuffer buffer, ParsedPacket packet) {

        if (buffer.remaining() < UDP_HEADER_LENGTH) {
            return false;
        }

        packet.setSourcePort(Short.toUnsignedInt(buffer.getShort()));
        packet.setDestinationPort(Short.toUnsignedInt(buffer.getShort()));

        // Skip Length
        buffer.getShort();

        // Skip Checksum
        buffer.getShort();

        // =============================
        // Save UDP Payload
        // =============================
        if (buffer.remaining() > 0) {

            byte[] payload = new byte[buffer.remaining()];
            buffer.get(payload);
            packet.setPayload(payload);

        } else {

            packet.setPayload(new byte[0]);
        }
        return true;
    }
}