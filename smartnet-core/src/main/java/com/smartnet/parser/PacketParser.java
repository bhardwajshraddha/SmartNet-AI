package com.smartnet.parser;

import com.smartnet.model.ParsedPacket;
import com.smartnet.model.RawPacket;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PacketParser {

    // Ethernet header:
    // 6 bytes Destination MAC
    // 6 bytes Source MAC
    // 2 bytes EtherType
    private static final int ETHERNET_HEADER_LENGTH = 14;
    private static final int MAC_ADDRESS_LENGTH = 6;

    /**
     * Parses a raw packet.
     * For now, only the Ethernet header is decoded.
     */
    public ParsedPacket parse(RawPacket rawPacket) {

        if (rawPacket == null || rawPacket.getData() == null) {
            return null;
        }

        byte[] data = rawPacket.getData();

        // A valid Ethernet frame must contain at least 14 bytes.
        if (data.length < ETHERNET_HEADER_LENGTH) {
            return null;
        }

        ParsedPacket packet = new ParsedPacket();

        // Network protocols use Big Endian (network byte order).
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.order(ByteOrder.BIG_ENDIAN);

        if (!parseEthernet(buffer, packet)) {
            return null;
        }

        return packet;
    }

    /**
     * Reads the Ethernet header and fills the packet object.
     */
    private boolean parseEthernet(ByteBuffer buffer, ParsedPacket packet) {

        if (buffer.remaining() < ETHERNET_HEADER_LENGTH) {
            return false;
        }

        byte[] destinationMac = new byte[MAC_ADDRESS_LENGTH];
        byte[] sourceMac = new byte[MAC_ADDRESS_LENGTH];

        // First 6 bytes -> Destination MAC
        buffer.get(destinationMac);

        // Next 6 bytes -> Source MAC
        buffer.get(sourceMac);

        // Last 2 bytes -> EtherType
        int etherType = Short.toUnsignedInt(buffer.getShort());

        packet.setDestinationMac(macToString(destinationMac));
        packet.setSourceMac(macToString(sourceMac));
        packet.setEtherType(etherType);

        return true;
    }

    /**
     * Converts a MAC address into the standard format.
     * Example:
     * 00:1A:2B:3C:4D:5E
     */
    private String macToString(byte[] mac) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < mac.length; i++) {

            builder.append(String.format("%02X", mac[i]));

            if (i < mac.length - 1) {
                builder.append(":");
            }
        }

        return builder.toString();
    }
}