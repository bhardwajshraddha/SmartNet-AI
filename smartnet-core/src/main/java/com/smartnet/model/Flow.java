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
     * Flow duration in seconds.
     */
    public long getDuration() {
        return Math.max(0, lastSeen - firstSeen);
    }

    /**
     * Average packet size in bytes.
     */
    public double getAveragePacketSize() {

        if (packetCount == 0) {
            return 0.0;
        }

        return (double) totalBytes / packetCount;
    }

    /**
     * Average packets per second.
     */
    public double getPacketsPerSecond() {

        long duration = getDuration();

        if (duration == 0) {
            return packetCount;
        }

        return (double) packetCount / duration;
    }

    /**
     * Updates this flow using the latest packet.
     */
    public void update(ParsedPacket packet) {

        if (packet == null) {
            return;
        }

        packetCount++;
        totalBytes += packet.getPacketLength();

        if (packetCount == 1) {
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
                "\n  averagePacketSize=" + String.format("%.2f", getAveragePacketSize()) + " bytes" +
                "\n  packetsPerSecond=" + String.format("%.2f", getPacketsPerSecond()) +
                "\n}";
    }
}