package com.smartnet.model;

public enum Protocol {

    ICMP(1),
    TCP(6),
    UDP(17),
    UNKNOWN(-1);

    private final int protocolNumber;

    Protocol(int protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public int getProtocolNumber() {
        return protocolNumber;
    }

    public static Protocol fromNumber(int number) {
        for (Protocol protocol : values()) {
            if (protocol.protocolNumber == number) {
                return protocol;
            }
        }
        return UNKNOWN;
    }
}