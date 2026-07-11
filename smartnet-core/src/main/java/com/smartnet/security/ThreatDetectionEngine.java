package com.smartnet.security;

import com.smartnet.model.ParsedPacket;
import com.smartnet.model.ThreatType;

public class ThreatDetectionEngine {
private final PortScanDetector portScanDetector = new PortScanDetector();
    public void detectThreat(ParsedPacket packet) {

    if (packet == null) {
        return;
    }

    int sourcePort = packet.getSourcePort();
    int destinationPort = packet.getDestinationPort();

    // Default
    packet.setThreatType(ThreatType.NONE);

    // Port Scan Detection (Highest Priority)
    if (portScanDetector.isPortScan(packet)) {
        packet.setThreatType(ThreatType.PORT_SCAN);
        return;
    }

    // Suspicious Ports
    if (sourcePort == 23 || destinationPort == 23) {
        packet.setThreatType(ThreatType.SUSPICIOUS_PORT);
    }

    else if (sourcePort == 445 || destinationPort == 445) {
        packet.setThreatType(ThreatType.SUSPICIOUS_PORT);
    }

    else if (sourcePort == 3389 || destinationPort == 3389) {
        packet.setThreatType(ThreatType.SUSPICIOUS_PORT);
    }
}
}