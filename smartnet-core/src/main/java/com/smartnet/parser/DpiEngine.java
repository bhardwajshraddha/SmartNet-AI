package com.smartnet.parser;

import com.smartnet.model.AppType;
import com.smartnet.model.ParsedPacket;

public class DpiEngine {

    /**
     * Detects the application protocol using well-known ports.
     */
    public void detectApplication(ParsedPacket packet) {

        if (packet == null) {
            return;
        }

        int sourcePort = packet.getSourcePort();
        int destinationPort = packet.getDestinationPort();

        if (sourcePort == 80 || destinationPort == 80) {
            packet.setAppType(AppType.HTTP);

        } else if (sourcePort == 443 || destinationPort == 443) {
            packet.setAppType(AppType.HTTPS);

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
}