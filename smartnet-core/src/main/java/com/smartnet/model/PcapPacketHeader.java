package com.smartnet.model;

public class PcapPacketHeader {

    private long timestampSeconds;
    private long timestampMicroseconds;
    private int includedLength;
    private int originalLength;

    public long getTimestampSeconds() {
        return timestampSeconds;
    }

    public void setTimestampSeconds(long timestampSeconds) {
        this.timestampSeconds = timestampSeconds;
    }

    public long getTimestampMicroseconds() {
        return timestampMicroseconds;
    }

    public void setTimestampMicroseconds(long timestampMicroseconds) {
        this.timestampMicroseconds = timestampMicroseconds;
    }

    public int getIncludedLength() {
        return includedLength;
    }

    public void setIncludedLength(int includedLength) {
        this.includedLength = includedLength;
    }

    public int getOriginalLength() {
        return originalLength;
    }

    public void setOriginalLength(int originalLength) {
        this.originalLength = originalLength;
    }
}
