package com.smartnet.model;

import com.smartnet.flow.FlowKey;

public class Flow {

    private final FlowKey flowKey;

    private int packetCount;
    private long totalBytes;

    private long firstSeen;
    private long lastSeen;

    public Flow(FlowKey flowKey) {
        this.flowKey = flowKey;
    }

    public FlowKey getFlowKey() {
        return flowKey;
    }

    public int getPacketCount() {
        return packetCount;
    }

    public long getTotalBytes() {
        return totalBytes;
    }

    public long getFirstSeen() {
        return firstSeen;
    }

    public long getLastSeen() {
        return lastSeen;
    }

    /**
     * Updates flow statistics whenever a new packet belongs to this flow.
     */
    public void update(ParsedPacket packet) {

        packetCount++;
        totalBytes += packet.getPacketLength();

        if (firstSeen == 0) {
            firstSeen = packet.getTimestamp();
        }

        lastSeen = packet.getTimestamp();
    }

    @Override
    public String toString() {

        return "Flow{" +
                "flow=" + flowKey +
                ", packets=" + packetCount +
                ", bytes=" + totalBytes +
                ", firstSeen=" + firstSeen +
                ", lastSeen=" + lastSeen +
                '}';
    }
}