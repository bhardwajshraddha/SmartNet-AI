package com.smartnet;

import com.smartnet.parser.PcapReader;

public class Main {

    public static void main(String[] args) {

        PcapReader reader = new PcapReader();

        reader.readGlobalHeader("src/test/resources/test_dpi.pcap");
    }
}
