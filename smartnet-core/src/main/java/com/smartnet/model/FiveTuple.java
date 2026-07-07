package com.smartnet.model;

public record FiveTuple(
        String sourceIp,
        String destinationIp,
        int sourcePort,
        int destinationPort,
        Protocol protocol
) {

    public FiveTuple reverse() {
        return new FiveTuple(
                destinationIp,
                sourceIp,
                destinationPort,
                sourcePort,
                protocol
        );
    }

    @Override
    public String toString() {
        return sourceIp + ":" + sourcePort +
                " -> " +
                destinationIp + ":" + destinationPort +
                " (" + protocol + ")";
    }
}