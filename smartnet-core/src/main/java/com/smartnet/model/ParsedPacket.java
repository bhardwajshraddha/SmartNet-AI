package com.smartnet.model;

import java.util.Arrays;

public class ParsedPacket {

    // =========================
    // Ethernet Layer
    // =========================
    private String sourceMac;
    private String destinationMac;
    private int etherType;

    // =========================
    // Network Layer
    // =========================
    private String sourceIp;
    private String destinationIp;

    // IPv4 Header Information
    private int ipVersion;
    private int ipHeaderLength;
    private int ttl;

    // =========================
    // Transport Layer
    // =========================
    private int sourcePort;
    private int destinationPort;
    private int tcpHeaderLength;
    private Protocol protocol;

    // =========================
    // Application Layer
    // =========================
    private AppType appType = AppType.UNKNOWN;
    private ThreatType threatType = ThreatType.NONE;
    private ThreatSeverity threatSeverity = ThreatSeverity.NONE;

    // TLS Server Name (SNI)
    private String serverName = "Unknown";

    // =========================
    // Packet Information
    // =========================
    private int packetLength;
    private byte[] payload;
    private long timestamp;

    public ParsedPacket() {
    }

    // =========================
    // Ethernet
    // =========================

    public String getSourceMac() {
        return sourceMac;
    }

    public void setSourceMac(String sourceMac) {
        this.sourceMac = sourceMac;
    }

    public String getDestinationMac() {
        return destinationMac;
    }

    public void setDestinationMac(String destinationMac) {
        this.destinationMac = destinationMac;
    }

    public int getEtherType() {
        return etherType;
    }

    public void setEtherType(int etherType) {
        this.etherType = etherType;
    }

    // =========================
    // IP
    // =========================

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getDestinationIp() {
        return destinationIp;
    }

    public void setDestinationIp(String destinationIp) {
        this.destinationIp = destinationIp;
    }

    public int getIpVersion() {
        return ipVersion;
    }

    public void setIpVersion(int ipVersion) {
        this.ipVersion = ipVersion;
    }

    public int getIpHeaderLength() {
        return ipHeaderLength;
    }

    public void setIpHeaderLength(int ipHeaderLength) {
        this.ipHeaderLength = ipHeaderLength;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    // =========================
    // Transport
    // =========================

    public int getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    public int getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(int destinationPort) {
        this.destinationPort = destinationPort;
    }

    public int getTcpHeaderLength() {
        return tcpHeaderLength;
    }

    public void setTcpHeaderLength(int tcpHeaderLength) {
        this.tcpHeaderLength = tcpHeaderLength;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    // =========================
    // Application
    // =========================

    public AppType getAppType() {
        return appType;
    }

    public void setAppType(AppType appType) {
        this.appType = appType;
    }

    // =========================
    // Threat Detection
    // =========================

    public ThreatType getThreatType() {
        return threatType;
    }

    public void setThreatType(ThreatType threatType) {
        this.threatType = threatType;
    }

    public ThreatSeverity getThreatSeverity() {
        return threatSeverity;
    }

    public void setThreatSeverity(ThreatSeverity threatSeverity) {
        this.threatSeverity = threatSeverity;
    }

    // =========================
    // TLS
    // =========================

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    // =========================
    // Packet Info
    // =========================

    public int getPacketLength() {
        return packetLength;
    }

    public void setPacketLength(int packetLength) {
        this.packetLength = packetLength;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {

        return "ParsedPacket{" +
                "sourceMac='" + sourceMac + '\'' +
                ", destinationMac='" + destinationMac + '\'' +
                ", etherType=0x" + Integer.toHexString(etherType).toUpperCase() +
                ", sourceIp='" + sourceIp + '\'' +
                ", destinationIp='" + destinationIp + '\'' +
                ", ipVersion=" + ipVersion +
                ", ipHeaderLength=" + ipHeaderLength +
                ", ttl=" + ttl +
                ", sourcePort=" + sourcePort +
                ", destinationPort=" + destinationPort +
                ", protocol=" + protocol +
                ", appType=" + appType +
                ", serverName='" + serverName + '\'' +
                ", threatType=" + threatType +
                ", threatSeverity=" + threatSeverity +
                ", packetLength=" + packetLength +
                ", timestamp=" + timestamp +
                ", payload=" + (payload == null ? "null" : Arrays.toString(payload)) +
                '}';
    }
}