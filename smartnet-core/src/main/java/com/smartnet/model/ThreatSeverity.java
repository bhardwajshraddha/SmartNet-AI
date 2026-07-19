package com.smartnet.model;

public enum ThreatSeverity {

    // No threat detected
    NONE,

    // Minor suspicious activity
    LOW,

    // Potential security risk
    MEDIUM,

    // Critical threat requiring attention
    HIGH;

    @Override
    public String toString() {
        return name();
    }
}