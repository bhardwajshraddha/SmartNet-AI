package com.smartnet.model;

public enum AppType {

    UNKNOWN,

    // ==========================
    // Network Protocols
    // ==========================

    HTTP,
    HTTPS,
    DNS,
    SSH,
    FTP,
    SMTP,
    TLS,
    QUIC,

    // ==========================
    // Web Services / Applications
    // ==========================

    GOOGLE,
    YOUTUBE,
    GITHUB,
    MICROSOFT,
    AMAZON,
    NETFLIX,
    FACEBOOK,
    INSTAGRAM,
    WHATSAPP,
    TELEGRAM,
    DISCORD,
    SPOTIFY,
    TIKTOK,
    ZOOM,

    OTHER;

    @Override
    public String toString() {
        return name();
    }
}