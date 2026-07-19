package com.smartnet.security;

import com.smartnet.model.ParsedPacket;
import com.smartnet.model.Protocol;
import com.smartnet.model.ThreatSeverity;
import com.smartnet.model.ThreatType;

public class ThreatDetectionEngine {

    /**
     * Detects simple security threats based on packet metadata.
     */
    public void detectThreat(ParsedPacket packet) {

        if (packet == null) {
            return;
        }

        packet.setThreatType(ThreatType.NONE);
        packet.setThreatSeverity(ThreatSeverity.NONE);

        // Invalid packet
        if (packet.getPacketLength() <= 0) {
            packet.setThreatType(ThreatType.INVALID_PACKET);
            packet.setThreatSeverity(ThreatSeverity.HIGH);
            return;
        }

        // Suspicious destination ports
        int port = packet.getDestinationPort();

        switch (port) {

            case 23: // Telnet
            case 135: // RPC
            case 139: // NetBIOS
            case 445: // SMB
            case 3389: // Remote Desktop

                packet.setThreatType(ThreatType.SUSPICIOUS_PORT);
                packet.setThreatSeverity(ThreatSeverity.MEDIUM);
                return;

            default:
                break;
        }

        // Unknown protocol
        if (packet.getProtocol() == null ||
                packet.getProtocol() == Protocol.UNKNOWN) {

            packet.setThreatType(ThreatType.UNKNOWN_PROTOCOL);
            packet.setThreatSeverity(ThreatSeverity.LOW);
        }
    }

    @Override
    public String toString() {
        return "ThreatDetectionEngine{}";
    }
}