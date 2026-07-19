package com.smartnet.parser;

import com.smartnet.model.AppType;
import com.smartnet.model.ParsedPacket;

public class DpiEngine {

    private final TlsParser tlsParser = new TlsParser();

    /**
     * Detects the application protocol using well-known ports.
     */
    public void detectApplication(ParsedPacket packet) {

        if (packet == null) {
            return;
        }

        // Default application
        packet.setAppType(AppType.UNKNOWN);

        int sourcePort = packet.getSourcePort();
        int destinationPort = packet.getDestinationPort();

        // ==========================
        // HTTP
        // ==========================
        if (sourcePort == 80 || destinationPort == 80) {

            packet.setAppType(AppType.HTTP);
            return;
        }

        // ==========================
        // HTTPS
        // ==========================
        if (sourcePort == 443 || destinationPort == 443) {

            packet.setAppType(AppType.HTTPS);

            // Extract TLS Server Name (SNI) only if payload exists
            if (packet.getPayload() != null &&
                    packet.getPayload().length > 0) {

                tlsParser.extractServerName(packet);
            }

            return;
        }

        // ==========================
        // DNS
        // ==========================
        if (sourcePort == 53 || destinationPort == 53) {

            packet.setAppType(AppType.DNS);
            return;
        }

        // ==========================
        // SSH
        // ==========================
        if (sourcePort == 22 || destinationPort == 22) {

            packet.setAppType(AppType.SSH);
            return;
        }

        // ==========================
        // FTP
        // ==========================
        if (sourcePort == 21 || destinationPort == 21) {

            packet.setAppType(AppType.FTP);
            return;
        }

        // ==========================
        // SMTP
        // ==========================
        if (sourcePort == 25 || destinationPort == 25) {

            packet.setAppType(AppType.SMTP);
        }
    }
}