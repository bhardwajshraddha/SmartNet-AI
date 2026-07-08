package com.smartnet.model;

public class PcapGlobalHeader {

    private final int magicNumber;
    private final short versionMajor;
    private final short versionMinor;
    private final int snaplen;
    private final int network;

    public PcapGlobalHeader(int magicNumber,
                            short versionMajor,
                            short versionMinor,
                            int snaplen,
                            int network) {

        this.magicNumber = magicNumber;
        this.versionMajor = versionMajor;
        this.versionMinor = versionMinor;
        this.snaplen = snaplen;
        this.network = network;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public short getVersionMajor() {
        return versionMajor;
    }

    public short getVersionMinor() {
        return versionMinor;
    }

    public int getSnaplen() {
        return snaplen;
    }

    public int getNetwork() {
        return network;
    }
}