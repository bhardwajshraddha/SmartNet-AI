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
     * Duration of the flow in seconds.
     */
    public long getDuration() {
        return lastSeen - firstSeen;
    }

    /**
     * Average packet size.
     */
    public double getAveragePacketSize() {

        if (packetCount == 0) {
            return 0;
        }

        return (double) totalBytes / packetCount;
    }

    /**
     * Packets processed per second.
     */
    public double getPacketsPerSecond() {

        long duration = getDuration();

        if (duration <= 0) {
            return packetCount;
        }

        return (double) packetCount / duration;
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
                "\n  flow=" + flowKey +
                "\n  packets=" + packetCount +
                "\n  bytes=" + totalBytes +
                "\n  firstSeen=" + firstSeen +
                "\n  lastSeen=" + lastSeen +
                "\n  duration=" + getDuration() + " sec" +
                "\n  avgPacketSize=" + String.format("%.2f", getAveragePacketSize()) + " bytes" +
                "\n  packetsPerSecond=" + String.format("%.2f", getPacketsPerSecond()) +
                "\n}";
    }
}