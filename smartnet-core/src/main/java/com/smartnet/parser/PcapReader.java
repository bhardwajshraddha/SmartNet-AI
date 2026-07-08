package com.smartnet.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PcapReader {

    public void readGlobalHeader(String filePath) {

        try (FileInputStream fis = new FileInputStream(filePath)) {

            byte[] header = new byte[24];

            int bytesRead = fis.read(header);

            if (bytesRead != 24) {
                System.out.println("Invalid PCAP file.");
                return;
            }

            ByteBuffer buffer = ByteBuffer.wrap(header);
            buffer.order(ByteOrder.LITTLE_ENDIAN);

            int magicNumber = buffer.getInt();
            short versionMajor = buffer.getShort();
            short versionMinor = buffer.getShort();

            buffer.getInt(); // thiszone
            buffer.getInt(); // sigfigs

            int snaplen = buffer.getInt();
            int network = buffer.getInt();

            System.out.println("========== PCAP GLOBAL HEADER ==========");
            System.out.printf("Magic Number : 0x%08X%n", magicNumber);
            System.out.println("Version      : " + versionMajor + "." + versionMinor);
            System.out.println("Snaplen      : " + snaplen);
            System.out.println("Network Type : " + network);
            System.out.println("========================================");

        } catch (IOException e) {
            System.out.println("Error reading PCAP file.");
            e.printStackTrace();
        }
    }
    public boolean isValidPcapFile(String filePath) {

    try (FileInputStream fis = new FileInputStream(filePath)) {

        byte[] magic = new byte[4];

        if (fis.read(magic) != 4) {
            return false;
        }

        return true;

    } catch (IOException e) {
        return false;
    }
}
}
