package com.smartnet;

import com.smartnet.model.ParsedPacket;
import com.smartnet.model.RawPacket;
import com.smartnet.parser.PacketParser;
import com.smartnet.parser.PcapReader;

public class Main {

    public static void main(String[] args) {

        String filePath = "../test-data/test_dpi.pcap";

        PcapReader reader = new PcapReader();

        if (!reader.isValidPcapFile(filePath)) {
            System.out.println("PCAP file not found.");
            return;
        }

        System.out.println("PCAP file found.");
        reader.readGlobalHeader(filePath);

        RawPacket packet = reader.readNextPacket(filePath);

        if (packet == null) {
            System.out.println("No packet found.");
            return;
        }

        System.out.println();
        System.out.println("===== FIRST PACKET =====");
        System.out.println("Timestamp : " + packet.getHeader().getTimestampSeconds());
        System.out.println("Length    : " + packet.getHeader().getIncludedLength());
        System.out.println("Data Size : " + packet.getData().length + " bytes");

        // Parse the raw packet into a readable format.
        PacketParser parser = new PacketParser();
        ParsedPacket parsedPacket = parser.parse(packet);

        if (parsedPacket == null) {
            System.out.println("\nPacket parsing failed.");
            return;
        }

        System.out.println();
        System.out.println("===== ETHERNET HEADER =====");
        System.out.println("Destination MAC : " + parsedPacket.getDestinationMac());
        System.out.println("Source MAC      : " + parsedPacket.getSourceMac());
        System.out.printf("EtherType       : 0x%04X%n", parsedPacket.getEtherType());
    }
}