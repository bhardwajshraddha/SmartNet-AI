package com.smartnet.parser;

import com.smartnet.model.ParsedPacket;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TlsParser {

    /**
     * Extracts the TLS Server Name (SNI) from the packet payload.
     * This is a lightweight implementation that searches for
     * domain-like strings inside the TLS ClientHello payload.
     */
    public void extractServerName(ParsedPacket packet) {

        if (packet == null ||
                packet.getPayload() == null ||
                packet.getPayload().length == 0) {
            return;
        }

        String payload = new String(packet.getPayload(), StandardCharsets.ISO_8859_1);

        Pattern pattern = Pattern.compile(
                "([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}");

        Matcher matcher = pattern.matcher(payload);

        while (matcher.find()) {

            String domain = matcher.group();

            if (isValidDomain(domain)) {
                packet.setServerName(domain.toLowerCase());
                return;
            }
        }

        packet.setServerName("Unknown");
    }

    /**
     * Basic validation to reduce false positives.
     */
    private boolean isValidDomain(String domain) {

        return domain.length() <= 255
                && domain.contains(".")
                && !domain.startsWith(".")
                && !domain.endsWith(".");
    }
}