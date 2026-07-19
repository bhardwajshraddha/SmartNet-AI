package com.smartnet.stats;

import com.smartnet.model.AppType;
import com.smartnet.model.CaptureStatistics;
import com.smartnet.model.ParsedPacket;
import com.smartnet.model.Protocol;

public class StatisticsEngine {

    private final CaptureStatistics statistics = new CaptureStatistics();

    /**
     * Updates packet statistics.
     */
    public void processPacket(ParsedPacket packet) {

        if (packet == null) {
            return;
        }

        // ==========================
        // Overall Statistics
        // ==========================

        statistics.incrementTotalPackets();
        statistics.addBytes(packet.getPacketLength());

        // ==========================
        // Protocol Statistics
        // ==========================

        Protocol protocol = packet.getProtocol();

        if (protocol != null) {

            switch (protocol) {

                case TCP:
                    statistics.incrementTcpPackets();
                    break;

                case UDP:
                    statistics.incrementUdpPackets();
                    break;

                case ICMP:
                    statistics.incrementIcmpPackets();
                    break;

                default:
                    break;
            }
        }

        // ==========================
        // Application Statistics
        // ==========================

        AppType app = packet.getAppType();

        if (app == null) {
            app = AppType.UNKNOWN;
        }

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
                break;
        }
    }

    public CaptureStatistics getStatistics() {
        return statistics;
    }

    @Override
    public String toString() {
        return statistics.toString();
    }
}