package com.smartnet.report;

import com.smartnet.flow.FlowTracker;
import com.smartnet.model.CaptureStatistics;
import com.smartnet.model.Flow;

public class ReportGenerator {

    public void printReport(CaptureStatistics statistics,
            FlowTracker flowTracker) {

        System.out.println();
        System.out.println("========== SMARTNET REPORT ==========");
        System.out.println();

        // Capture Statistics
        System.out.println(statistics);

        // Flow Details
        System.out.println();
        System.out.println("========== FLOW ANALYSIS ==========");

        for (Flow flow : flowTracker.getFlows()) {

            System.out.println("----------------------------------------");
            System.out.println(flow.getFlowKey());

            System.out.println("Packets            : " + flow.getPacketCount());
            System.out.println("Bytes              : " + flow.getTotalBytes());
            System.out.println("Duration           : " + flow.getDuration() + " sec");
            System.out.printf("Average Packet Size: %.2f bytes%n",
                    flow.getAveragePacketSize());
            System.out.printf("Packets / Second   : %.2f%n",
                    flow.getPacketsPerSecond());

            System.out.println("----------------------------------------");
            System.out.println();
        }

        System.out.println("========== END OF REPORT ==========");
    }
}