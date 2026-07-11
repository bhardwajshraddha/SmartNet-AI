package com.smartnet.security;

import com.smartnet.model.ParsedPacket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PortScanDetector {

    private final Map<String, Set<Integer>> scannedPorts = new HashMap<>();

    public boolean isPortScan(ParsedPacket packet) {

        if (packet == null) {
            return false;
        }

        String sourceIp = packet.getSourceIp();

        scannedPorts.putIfAbsent(sourceIp, new HashSet<>());

        scannedPorts.get(sourceIp).add(packet.getDestinationPort());

        return scannedPorts.get(sourceIp).size() >= 5;
    }
}