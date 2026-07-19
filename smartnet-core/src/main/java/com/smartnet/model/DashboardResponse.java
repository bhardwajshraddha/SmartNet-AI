package com.smartnet.model;

import java.util.Collection;

public class DashboardResponse {

    private CaptureStatistics statistics;
    private Collection<Flow> flows;

    public DashboardResponse() {
    }

    public DashboardResponse(CaptureStatistics statistics,
            Collection<Flow> flows) {
        this.statistics = statistics;
        this.flows = flows;
    }

    public CaptureStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(CaptureStatistics statistics) {
        this.statistics = statistics;
    }

    public Collection<Flow> getFlows() {
        return flows;
    }

    public void setFlows(Collection<Flow> flows) {
        this.flows = flows;
    }

    @Override
    public String toString() {
        return "DashboardResponse{" +
                "statistics=" + statistics +
                ", flows=" + flows +
                '}';
    }
}