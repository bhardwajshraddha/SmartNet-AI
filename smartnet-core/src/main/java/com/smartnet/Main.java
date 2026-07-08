package com.smartnet;

import com.smartnet.parser.PcapReader;

public class Main {

    public static void main(String[] args) {
        String filePath = "../test-data/test_dpi.pcap";

        PcapReader reader = new PcapReader();

        if (reader.isValidPcapFile(filePath)) {
            System.out.println("PCAP file found.");
            reader.readGlobalHeader(filePath);
        } else {
            System.out.println("PCAP file not found.");
        }
    }
}