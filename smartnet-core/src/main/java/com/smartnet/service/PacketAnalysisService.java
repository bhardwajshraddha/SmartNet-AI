package com.smartnet.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smartnet.flow.FlowTracker;
import com.smartnet.model.AnalysisResult;
import com.smartnet.model.CaptureStatistics;
import com.smartnet.model.DashboardResponse;
import com.smartnet.model.DashboardSummary;
import com.smartnet.model.ParsedPacket;
import com.smartnet.model.RawPacket;
import com.smartnet.model.UploadResponse;
import com.smartnet.parser.DpiEngine;
import com.smartnet.parser.PacketParser;
import com.smartnet.parser.PcapReader;
import com.smartnet.security.ThreatDetectionEngine;
import com.smartnet.stats.StatisticsEngine;
import com.smartnet.model.ProtocolSummary;

@Service
public class PacketAnalysisService {

    public AnalysisResult analyze(String filePath) {

        PcapReader reader = new PcapReader();
        PacketParser parser = new PacketParser();
        DpiEngine dpiEngine = new DpiEngine();
        ThreatDetectionEngine threatEngine = new ThreatDetectionEngine();

        StatisticsEngine statisticsEngine = new StatisticsEngine();
        FlowTracker flowTracker = new FlowTracker();

        if (!reader.open(filePath)) {
            throw new RuntimeException("Unable to open PCAP file: " + filePath);
        }

        RawPacket rawPacket;

        while ((rawPacket = reader.readNextPacket()) != null) {

            ParsedPacket packet = parser.parse(rawPacket);

            if (packet == null) {
                continue;
            }

            dpiEngine.detectApplication(packet);
            threatEngine.detectThreat(packet);

            statisticsEngine.processPacket(packet);
            flowTracker.processPacket(packet);
        }

        reader.close();

        CaptureStatistics statistics = statisticsEngine.getStatistics();

        return new AnalysisResult(
                statistics,
                flowTracker.getFlows());
    }

    public DashboardResponse getDashboard(String filePath) {

        AnalysisResult result = analyze(filePath);

        return new DashboardResponse(
                result.getStatistics(),
                result.getFlows());
    }

    public UploadResponse uploadAndAnalyze(MultipartFile file) throws IOException {

        System.out.println("========== Upload Started ==========");

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("No file uploaded.");
        }

        System.out.println("File : " + file.getOriginalFilename());
        // Create uploads directory in project root
        File uploadDir = new File(System.getProperty("user.dir"), "uploads");

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Save uploaded file
        File destination = new File(uploadDir, file.getOriginalFilename());

        System.out.println("Destination Path : " + destination.getAbsolutePath());

        file.transferTo(destination);

        System.out.println("File Saved Successfully");

        AnalysisResult result = analyze(destination.getAbsolutePath());

        System.out.println("Analysis Completed");

        UploadResponse response = new UploadResponse(
                file.getOriginalFilename(),
                result);

        System.out.println("Response Created");

        return response;
    }

    public DashboardSummary getDashboardSummary(String filePath) {

        AnalysisResult result = analyze(filePath);

        DashboardSummary summary = new DashboardSummary();

        CaptureStatistics stats = result.getStatistics();

        summary.setTotalPackets(stats.getTotalPackets());
        summary.setTotalFlows(result.getFlows().size());

        summary.setTcpPackets(stats.getTcpPackets());
        summary.setUdpPackets(stats.getUdpPackets());

        summary.setHttpPackets(stats.getHttpPackets());
        summary.setHttpsPackets(stats.getHttpsPackets());
        summary.setDnsPackets(stats.getDnsPackets());

        summary.setTotalThreats(stats.getTotalThreats());

        if (stats.getTotalPackets() > 0) {

            summary.setAveragePacketSize(
                    (double) stats.getTotalBytes() / stats.getTotalPackets());

        } else {

            summary.setAveragePacketSize(0);
        }

        return summary;
    }

    public List<ProtocolSummary> getProtocolSummary(String filePath) {

        AnalysisResult result = analyze(filePath);

        CaptureStatistics stats = result.getStatistics();

        List<ProtocolSummary> protocols = new ArrayList<>();

        protocols.add(new ProtocolSummary("TCP", stats.getTcpPackets()));
        protocols.add(new ProtocolSummary("UDP", stats.getUdpPackets()));
        protocols.add(new ProtocolSummary("HTTP", stats.getHttpPackets()));
        protocols.add(new ProtocolSummary("HTTPS", stats.getHttpsPackets()));
        protocols.add(new ProtocolSummary("DNS", stats.getDnsPackets()));

        return protocols;
    }

   
}