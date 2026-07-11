package com.smartnet.stats;

import com.smartnet.model.AppType;
import com.smartnet.model.CaptureStatistics;
import com.smartnet.model.ParsedPacket;
import com.smartnet.model.Protocol;

public class StatisticsEngine {

    private final CaptureStatistics statistics = new CaptureStatistics();

    public void processPacket(ParsedPacket packet) {

        if (packet == null) {
            return;
        }

        statistics.incrementTotalPackets();
        statistics.addBytes(packet.getPacketLength());

        if (packet.getProtocol() == Protocol.TCP) {
            statistics.incrementTcpPackets();

        } else if (packet.getProtocol() == Protocol.UDP) {
            statistics.incrementUdpPackets();

        } else if (packet.getProtocol() == Protocol.ICMP) {
            statistics.incrementIcmpPackets();
        }

        AppType app = packet.getAppType();

        switch (app) {

            case HTTP:
                statistics.incrementHttpPackets();
                break;

            case HTTPS:
                statistics.incrementHttpsPackets();
                break;

            case DNS:
                statistics.incrementDnsPackets();
                break;

            case SSH:
                statistics.incrementSshPackets();
                break;

            case FTP:
                statistics.incrementFtpPackets();
                break;

            case SMTP:
                statistics.incrementSmtpPackets();
                break;

            default:
                statistics.incrementUnknownPackets();
        }
    }

    public CaptureStatistics getStatistics() {
        return statistics;
    }
}