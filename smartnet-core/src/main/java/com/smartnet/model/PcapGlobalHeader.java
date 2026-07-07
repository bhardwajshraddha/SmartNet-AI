package com.smartnet.model;

public class PcapGlobalHeader {

    private int magicNumber;
    private short versionMajor;
    private short versionMinor;
    private int thisZone;
    private int sigFigs;
    private int snapLen;
    private int network;

    public int getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public short getVersionMajor() {
        return versionMajor;
    }

    public void setVersionMajor(short versionMajor) {
        this.versionMajor = versionMajor;
    }

    public short getVersionMinor() {
        return versionMinor;
    }

    public void setVersionMinor(short versionMinor) {
        this.versionMinor = versionMinor;
    }

    public int getThisZone() {
        return thisZone;
    }

    public void setThisZone(int thisZone) {
        this.thisZone = thisZone;
    }

    public int getSigFigs() {
        return sigFigs;
    }

    public void setSigFigs(int sigFigs) {
        this.sigFigs = sigFigs;
    }

    public int getSnapLen() {
        return snapLen;
    }

    public void setSnapLen(int snapLen) {
        this.snapLen = snapLen;
    }

    public int getNetwork() {
        return network;
    }

    public void setNetwork(int network) {
        this.network = network;
    }
}