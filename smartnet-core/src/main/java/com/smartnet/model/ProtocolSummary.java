package com.smartnet.model;

public class ProtocolSummary {

    private String protocol;
    private int packets;

    public ProtocolSummary() {
    }

    public ProtocolSummary(String protocol, int packets) {
        this.protocol = protocol;
        this.packets = packets;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getPackets() {
        return packets;
    }

    public void setPackets(int packets) {
        this.packets = packets;
    }

    @Override
    public String toString() {
        return "ProtocolSummary{" +
                "protocol='" + protocol + '\'' +
                ", packets=" + packets +
                '}';
    }
}