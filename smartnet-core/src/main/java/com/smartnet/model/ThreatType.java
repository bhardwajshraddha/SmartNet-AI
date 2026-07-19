package com.smartnet.model;

public enum ThreatType {

    // No threat detected
    NONE,

    // Traffic using risky or commonly attacked ports
    SUSPICIOUS_PORT,

    // Multiple connection attempts to different ports
    PORT_SCAN,

    // Denial-of-Service attack pattern
    DOS_ATTACK,

    // Malformed or invalid packet
    INVALID_PACKET,

    // Protocol could not be identified
    UNKNOWN_PROTOCOL;

    @Override
    public String toString() {
        return name();
    }
}