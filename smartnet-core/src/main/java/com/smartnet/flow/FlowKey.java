package com.smartnet.flow;

import java.util.Objects;

public class FlowKey {

    private final String sourceIp;
    private final String destinationIp;
    private final int sourcePort;
    private final int destinationPort;
    private final String protocol;

    public FlowKey(String sourceIp,
            String destinationIp,
            int sourcePort,
            int destinationPort,
            String protocol) {

        // Normalize the flow so both directions map to the same key.
        String endpoint1 = sourceIp + ":" + sourcePort;
        String endpoint2 = destinationIp + ":" + destinationPort;

        if (endpoint1.compareTo(endpoint2) <= 0) {
            this.sourceIp = sourceIp;
            this.destinationIp = destinationIp;
            this.sourcePort = sourcePort;
            this.destinationPort = destinationPort;
        } else {
            this.sourceIp = destinationIp;
            this.destinationIp = sourceIp;
            this.sourcePort = destinationPort;
            this.destinationPort = sourcePort;
        }

        this.protocol = protocol;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public String getDestinationIp() {
        return destinationIp;
    }

    public int getSourcePort() {
        return sourcePort;
    }

    public int getDestinationPort() {
        return destinationPort;
    }

    public String getProtocol() {
        return protocol;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof FlowKey other)) {
            return false;
        }

        return sourcePort == other.sourcePort
                && destinationPort == other.destinationPort
                && Objects.equals(sourceIp, other.sourceIp)
                && Objects.equals(destinationIp, other.destinationIp)
                && Objects.equals(protocol, other.protocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                sourceIp,
                destinationIp,
                sourcePort,
                destinationPort,
                protocol);
    }

    @Override
    public String toString() {

        return sourceIp + ":" + sourcePort
                + " ⇄ "
                + destinationIp + ":" + destinationPort
                + " (" + protocol + ")";
    }
}