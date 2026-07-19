package com.smartnet.model;

import java.util.Collection;

public class AnalysisResult {

    private CaptureStatistics statistics;
    private Collection<Flow> flows;

    public AnalysisResult() {
    }

    public AnalysisResult(CaptureStatistics statistics, Collection<Flow> flows) {
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
}