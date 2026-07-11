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

        System.out.println(statistics);

        System.out.println();
        System.out.println("===== FLOWS =====");

        for (Flow flow : flowTracker.getFlows()) {
            System.out.println(flow);
        }

        System.out.println();
        System.out.println("========== END OF REPORT ==========");
    }
}
