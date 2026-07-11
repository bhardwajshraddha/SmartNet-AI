package com.smartnet.model;

public class CaptureStatistics {

    private int totalPackets;
    private long totalBytes;

    private int tcpPackets;
    private int udpPackets;
    private int icmpPackets;

    private int httpPackets;
    private int httpsPackets;
    private int dnsPackets;
    private int sshPackets;
    private int ftpPackets;
    private int smtpPackets;
    private int unknownPackets;

    public int getTotalPackets() {
        return totalPackets;
    }

    public void incrementTotalPackets() {
        totalPackets++;
    }

    public long getTotalBytes() {
        return totalBytes;
    }

    public void addBytes(long bytes) {
        totalBytes += bytes;
    }

    public int getTcpPackets() {
        return tcpPackets;
    }

    public void incrementTcpPackets() {
        tcpPackets++;
    }

    public int getUdpPackets() {
        return udpPackets;
    }

    public void incrementUdpPackets() {
        udpPackets++;
    }

    public int getIcmpPackets() {
        return icmpPackets;
    }

    public void incrementIcmpPackets() {
        icmpPackets++;
    }

    public int getHttpPackets() {
        return httpPackets;
    }

    public void incrementHttpPackets() {
        httpPackets++;
    }

    public int getHttpsPackets() {
        return httpsPackets;
    }

    public void incrementHttpsPackets() {
        httpsPackets++;
    }

    public int getDnsPackets() {
        return dnsPackets;
    }

    public void incrementDnsPackets() {
        dnsPackets++;
    }

    public int getSshPackets() {
        return sshPackets;
    }

    public void incrementSshPackets() {
        sshPackets++;
    }

    public int getFtpPackets() {
        return ftpPackets;
    }

    public void incrementFtpPackets() {
        ftpPackets++;
    }

    public int getSmtpPackets() {
        return smtpPackets;
    }

    public void incrementSmtpPackets() {
        smtpPackets++;
    }

    public int getUnknownPackets() {
        return unknownPackets;
    }

    public void incrementUnknownPackets() {
        unknownPackets++;
    }

    @Override
    public String toString() {

        return """
                
                ===== CAPTURE STATISTICS =====
                Total Packets : %d
                Total Bytes   : %d
                
                TCP Packets   : %d
                UDP Packets   : %d
                ICMP Packets  : %d
                
                HTTP          : %d
                HTTPS         : %d
                DNS           : %d
                SSH           : %d
                FTP           : %d
                SMTP          : %d
                Unknown       : %d
                """
                .formatted(
                        totalPackets,
                        totalBytes,
                        tcpPackets,
                        udpPackets,
                        icmpPackets,
                        httpPackets,
                        httpsPackets,
                        dnsPackets,
                        sshPackets,
                        ftpPackets,
                        smtpPackets,
                        unknownPackets
                );
    }
}