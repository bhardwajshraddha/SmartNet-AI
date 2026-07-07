package com.smartnet.model;

public class RawPacket {

    private PcapPacketHeader header;
    private byte[] data;

    public RawPacket() {
    }

    public RawPacket(PcapPacketHeader header, byte[] data) {
        this.header = header;
        this.data = data;
    }

    public PcapPacketHeader getHeader() {
        return header;
    }

    public void setHeader(PcapPacketHeader header) {
        this.header = header;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}