package com.smartnet.flow;

import com.smartnet.model.Flow;
import com.smartnet.model.ParsedPacket;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FlowTracker {

    private final Map<FlowKey, Flow> flows = new HashMap<>();

    /**
     * Adds a packet to its corresponding flow.
     * Creates a new flow if it does not already exist.
     */
    public void processPacket(ParsedPacket packet) {

        if (packet == null ||
                packet.getProtocol() == null ||
                packet.getSourceIp() == null ||
                packet.getDestinationIp() == null) {
            return;
        }

        FlowKey key = new FlowKey(
                packet.getSourceIp(),
                packet.getDestinationIp(),
                packet.getSourcePort(),
                packet.getDestinationPort(),
                packet.getProtocol().name());

        Flow flow = flows.computeIfAbsent(key, Flow::new);

        flow.update(packet);
    }

    /**
     * Returns all tracked flows.
     */
    public Collection<Flow> getFlows() {
        return flows.values();
    }

    /**
     * Returns the number of tracked flows.
     */
    public int getFlowCount() {
        return flows.size();
    }

    /**
     * Clears all tracked flows.
     */
    public void clear() {
        flows.clear();
    }
}