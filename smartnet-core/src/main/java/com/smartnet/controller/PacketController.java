package com.smartnet.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smartnet.model.AnalysisResult;
import com.smartnet.model.CaptureStatistics;
import com.smartnet.model.DashboardResponse;
import com.smartnet.model.DashboardSummary;
import com.smartnet.model.Flow;
import com.smartnet.model.ProtocolSummary;
import com.smartnet.model.UploadResponse;
import com.smartnet.service.PacketAnalysisService;

@RestController
public class PacketController {

    private static final String PCAP_FILE = "../test-data/test_dpi.pcap";

    private final PacketAnalysisService packetAnalysisService;

    public PacketController(PacketAnalysisService packetAnalysisService) {
        this.packetAnalysisService = packetAnalysisService;
    }

    // ==========================
    // Health Check
    // ==========================

    @GetMapping("/api/health")
    public String health() {
        return "SmartNet API is running";
    }

    // ==========================
    // Complete Analysis
    // ==========================

    @GetMapping("/api/analysis")
    public AnalysisResult getAnalysis() {
        return packetAnalysisService.analyze(PCAP_FILE);
    }

    // ==========================
    // Dashboard
    // ==========================

    @GetMapping("/api/dashboard")
    public DashboardResponse getDashboard() {
        return packetAnalysisService.getDashboard(PCAP_FILE);
    }

    @GetMapping("/api/dashboard-summary")
    public DashboardSummary getDashboardSummary() {
        return packetAnalysisService.getDashboardSummary(PCAP_FILE);
    }

    @GetMapping("/api/protocol-summary")
    public List<ProtocolSummary> getProtocolSummary() {
        return packetAnalysisService.getProtocolSummary(PCAP_FILE);
    }

    // ==========================
    // Statistics
    // ==========================

    @GetMapping("/api/statistics")
    public CaptureStatistics getStatistics() {
        return packetAnalysisService.analyze(PCAP_FILE).getStatistics();
    }

    // ==========================
    // Flows
    // ==========================

    @GetMapping("/api/flows")
    public Collection<Flow> getFlows() {
        return packetAnalysisService.analyze(PCAP_FILE).getFlows();
    }

    // ==========================
    // Upload PCAP File
    // ==========================

    @PostMapping("/api/upload")
    public UploadResponse uploadFile(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        return packetAnalysisService.uploadAndAnalyze(file);
    }
}