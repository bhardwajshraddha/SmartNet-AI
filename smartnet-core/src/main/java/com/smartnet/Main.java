package com.smartnet;

import com.smartnet.security.ThreatDetectionEngine;
import com.smartnet.report.ReportGenerator;
import com.smartnet.model.CaptureStatistics;
import com.smartnet.model.ParsedPacket;
import com.smartnet.model.Protocol;
import com.smartnet.model.RawPacket;
import com.smartnet.parser.PacketParser;
import com.smartnet.parser.DpiEngine;
import com.smartnet.parser.PcapReader;
import com.smartnet.flow.FlowTracker;
import com.smartnet.stats.StatisticsEngine;

public class Main {

    public static void main(String[] args) {

        String filePath = "../test-data/test_dpi.pcap";

        PcapReader reader = new PcapReader();
        PacketParser parser = new PacketParser();
        FlowTracker flowTracker = new FlowTracker();
        StatisticsEngine statisticsEngine = new StatisticsEngine();
        ReportGenerator reportGenerator = new ReportGenerator();
        ThreatDetectionEngine threatDetectionEngine = new ThreatDetectionEngine();
        DpiEngine dpiEngine = new DpiEngine();

        if (!reader.isValidPcapFile(filePath)) {
            System.out.println("PCAP file not found.");
            return;
        }

        System.out.println("PCAP file found.");
        reader.readGlobalHeader(filePath);

        // Open the PCAP for sequential packet reading
        if (!reader.open(filePath)) {
            System.out.println("Failed to open PCAP file.");
            return;
        }

        RawPacket packet;

        while ((packet = reader.readNextPacket()) != null) {

            System.out.println();
            System.out.println("===== PACKET =====");
            System.out.println("Timestamp : " + packet.getHeader().getTimestampSeconds());
            System.out.println("Length    : " + packet.getHeader().getIncludedLength());
            System.out.println("Data Size : " + packet.getData().length + " bytes");

            ParsedPacket parsedPacket = parser.parse(packet);

            dpiEngine.detectApplication(parsedPacket);
            threatDetectionEngine.detectThreat(parsedPacket);

            if (parsedPacket != null) {

                System.out.println();
                System.out.println("===== ETHERNET HEADER =====");
                System.out.println("Destination MAC : " + parsedPacket.getDestinationMac());
                System.out.println("Source MAC      : " + parsedPacket.getSourceMac());
                System.out.printf("EtherType       : 0x%04X%n", parsedPacket.getEtherType());

                if (parsedPacket.getIpVersion() == 4) {

                    System.out.println();
                    System.out.println("===== IPV4 HEADER =====");
                    System.out.println("Version         : " + parsedPacket.getIpVersion());
                    System.out.println("Header Length   : " + parsedPacket.getIpHeaderLength() + " bytes");
                    System.out.println("TTL             : " + parsedPacket.getTtl());
                    System.out.println("Protocol        : " + parsedPacket.getProtocol());
                    System.out.println("Source IP       : " + parsedPacket.getSourceIp());
                    System.out.println("Destination IP  : " + parsedPacket.getDestinationIp());
                }

                if (parsedPacket.getProtocol() == Protocol.TCP) {

                    System.out.println();
                    System.out.println("===== TCP HEADER =====");
                    System.out.println("Source Port      : " + parsedPacket.getSourcePort());
                    System.out.println("Destination Port : " + parsedPacket.getDestinationPort());
                    System.out.println("Header Length    : " + parsedPacket.getTcpHeaderLength() + " bytes");
                }

                System.out.println();
                System.out.println("===== APPLICATION =====");

                System.out.println();
                System.out.println("===== DEBUG =====");

                if (parsedPacket.getPayload() == null) {
                    System.out.println("Payload : NULL");
                } else {
                    System.out.println("Payload Size : " + parsedPacket.getPayload().length);
                }

                System.out.println("Application : " + parsedPacket.getAppType());

                // ===== NEW =====
                if (parsedPacket.getAppType() == com.smartnet.model.AppType.HTTPS) {
                    System.out.println("Server Name : " + parsedPacket.getServerName());
                }

                System.out.println();
                System.out.println("===== THREAT =====");
                System.out.println("Threat : " + parsedPacket.getThreatType());

                if (parsedPacket.getProtocol() == Protocol.UDP) {

                    System.out.println();
                    System.out.println("===== UDP HEADER =====");
                    System.out.println("Source Port      : " + parsedPacket.getSourcePort());
                    System.out.println("Destination Port : " + parsedPacket.getDestinationPort());
                }

                // Process packet
                flowTracker.processPacket(parsedPacket);
                statisticsEngine.processPacket(parsedPacket);

            } else {
                System.out.println("Failed to parse the packet.");
            }

        }

        reader.close();

        // Generate report
        CaptureStatistics statistics = statisticsEngine.getStatistics();
        reportGenerator.printReport(statistics, flowTracker);

    }
}