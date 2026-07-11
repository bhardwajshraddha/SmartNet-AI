package com.smartnet.parser;

import com.smartnet.model.PcapPacketHeader;
import com.smartnet.model.RawPacket;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PcapReader {

    private FileInputStream fis;

    /**
     * Opens the PCAP file and skips the global header.
     */
    public boolean open(String filePath) {

        try {

            fis = new FileInputStream(filePath);

            byte[] globalHeader = new byte[24];

            if (fis.read(globalHeader) != 24) {
                return false;
            }

            return true;

        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Closes the PCAP file.
     */
    public void close() {

        if (fis != null) {

            try {
                fis.close();
            } catch (IOException ignored) {
            }
        }
    }

    /**
     * Reads and prints the PCAP global header.
     */
    public void readGlobalHeader(String filePath) {

        try (FileInputStream input = new FileInputStream(filePath)) {

            byte[] header = new byte[24];

            if (input.read(header) != 24) {
                System.out.println("Invalid PCAP file.");
                return;
            }

            ByteBuffer buffer = ByteBuffer.wrap(header);
            buffer.order(ByteOrder.LITTLE_ENDIAN);

            int magicNumber = buffer.getInt();
            short versionMajor = buffer.getShort();
            short versionMinor = buffer.getShort();

            buffer.getInt();
            buffer.getInt();

            int snaplen = buffer.getInt();
            int network = buffer.getInt();

            System.out.println("========== PCAP GLOBAL HEADER ==========");
            System.out.printf("Magic Number : 0x%08X%n", magicNumber);
            System.out.println("Version      : " + versionMajor + "." + versionMinor);
            System.out.println("Snaplen      : " + snaplen);
            System.out.println("Network Type : " + network);
            System.out.println("========================================");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidPcapFile(String filePath) {

        try (FileInputStream input = new FileInputStream(filePath)) {

            return input.read() != -1;

        } catch (IOException e) {

            return false;
        }
    }

    /**
     * Reads the next packet from the already-open file.
     */
    public RawPacket readNextPacket() {

        if (fis == null) {
            return null;
        }

        try {

            byte[] packetHeaderBytes = new byte[16];

            if (fis.read(packetHeaderBytes) != 16) {
                return null;
            }

            ByteBuffer buffer = ByteBuffer.wrap(packetHeaderBytes);
            buffer.order(ByteOrder.LITTLE_ENDIAN);

            PcapPacketHeader header = new PcapPacketHeader();

            header.setTimestampSeconds(Integer.toUnsignedLong(buffer.getInt()));
            header.setTimestampMicroseconds(Integer.toUnsignedLong(buffer.getInt()));
            header.setIncludedLength(buffer.getInt());
            header.setOriginalLength(buffer.getInt());

            byte[] data = new byte[header.getIncludedLength()];

            if (fis.read(data) != header.getIncludedLength()) {
                return null;
            }

            return new RawPacket(header, data);

        } catch (IOException e) {

            return null;
        }
    }
}