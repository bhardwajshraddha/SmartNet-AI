package com.smartnet.parser;

import com.smartnet.model.AppType;
import com.smartnet.model.ParsedPacket;

public class DpiEngine {

    private final TlsParser tlsParser = new TlsParser();

    public void detectApplication(ParsedPacket packet) {

        if (packet == null) {
            return;
        }

        int sourcePort = packet.getSourcePort();
        int destinationPort = packet.getDestinationPort();

        // -------------------------
        // Port-based Detection
        // -------------------------

        if (sourcePort == 80 || destinationPort == 80) {

            packet.setAppType(AppType.HTTP);

            detectHttpHost(packet);

        } else if (sourcePort == 443 || destinationPort == 443) {

            packet.setAppType(AppType.HTTPS);

            // Extract TLS Server Name
            tlsParser.extractServerName(packet);

            classifyByDomain(packet);

        } else if (sourcePort == 53 || destinationPort == 53) {

            packet.setAppType(AppType.DNS);

        } else if (sourcePort == 22 || destinationPort == 22) {

            packet.setAppType(AppType.SSH);

        } else if (sourcePort == 21 || destinationPort == 21) {

            packet.setAppType(AppType.FTP);

        } else if (sourcePort == 25 || destinationPort == 25) {

            packet.setAppType(AppType.SMTP);

        } else {

            packet.setAppType(AppType.UNKNOWN);
        }
    }

    /**
     * Detect application using extracted TLS SNI.
     */
    private void classifyByDomain(ParsedPacket packet) {

        String domain = packet.getServerName();

        if (domain == null) {
            return;
        }

        domain = domain.toLowerCase();

        if (domain.contains("google")) {

            packet.setAppType(AppType.GOOGLE);

        } else if (domain.contains("youtube")) {

            packet.setAppType(AppType.YOUTUBE);

        } else if (domain.contains("github")) {

            packet.setAppType(AppType.GITHUB);

        } else if (domain.contains("openai")
                || domain.contains("chatgpt")) {

            packet.setAppType(AppType.OTHER);

        } else if (domain.contains("facebook")) {

            packet.setAppType(AppType.FACEBOOK);

        } else if (domain.contains("instagram")) {

            packet.setAppType(AppType.INSTAGRAM);

        } else if (domain.contains("amazon")) {

            packet.setAppType(AppType.AMAZON);

        } else if (domain.contains("microsoft")) {

            packet.setAppType(AppType.MICROSOFT);
        }
    }

    private void detectHttpHost(ParsedPacket packet) {

        if (packet.getPayload() == null ||
                packet.getPayload().length == 0) {
            return;
        }

        String payload = new String(packet.getPayload());

        for (String line : payload.split("\\r?\\n")) {

            if (line.toLowerCase().startsWith("host:")) {

                String host = line.substring(5).trim();

                packet.setServerName(host);

                return;
            }
        }
    }
}