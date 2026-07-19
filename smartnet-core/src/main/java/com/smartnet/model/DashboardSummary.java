package com.smartnet.model;

public class DashboardSummary {

    private int totalPackets;
    private int totalFlows;

    private int tcpPackets;
    private int udpPackets;

    private int httpPackets;
    private int httpsPackets;
    private int dnsPackets;

    private int totalThreats;

    private double averagePacketSize;

    public DashboardSummary() {
    }

    public int getTotalPackets() {
        return totalPackets;
    }

    public void setTotalPackets(int totalPackets) {
        this.totalPackets = totalPackets;
    }

    public int getTotalFlows() {
        return totalFlows;
    }

    public void setTotalFlows(int totalFlows) {
        this.totalFlows = totalFlows;
    }

    public int getTcpPackets() {
        return tcpPackets;
    }

    public void setTcpPackets(int tcpPackets) {
        this.tcpPackets = tcpPackets;
    }

    public int getUdpPackets() {
        return udpPackets;
    }

    public void setUdpPackets(int udpPackets) {
        this.udpPackets = udpPackets;
    }

    public int getHttpPackets() {
        return httpPackets;
    }

    public void setHttpPackets(int httpPackets) {
        this.httpPackets = httpPackets;
    }

    public int getHttpsPackets() {
        return httpsPackets;
    }

    public void setHttpsPackets(int httpsPackets) {
        this.httpsPackets = httpsPackets;
    }

    public int getDnsPackets() {
        return dnsPackets;
    }

    public void setDnsPackets(int dnsPackets) {
        this.dnsPackets = dnsPackets;
    }

    public int getTotalThreats() {
        return totalThreats;
    }

    public void setTotalThreats(int totalThreats) {
        this.totalThreats = totalThreats;
    }

    public double getAveragePacketSize() {
        return averagePacketSize;
    }

    public void setAveragePacketSize(double averagePacketSize) {
        this.averagePacketSize = averagePacketSize;
    }

    @Override
    public String toString() {
        return "DashboardSummary{" +
                "totalPackets=" + totalPackets +
                ", totalFlows=" + totalFlows +
                ", tcpPackets=" + tcpPackets +
                ", udpPackets=" + udpPackets +
                ", httpPackets=" + httpPackets +
                ", httpsPackets=" + httpsPackets +
                ", dnsPackets=" + dnsPackets +
                ", totalThreats=" + totalThreats +
                ", averagePacketSize=" + averagePacketSize +
                '}';
    }
}