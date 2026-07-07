package com.smartnet.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteUtils {

    private ByteUtils() {
    }

    public static int readIntLittleEndian(byte[] data, int offset) {
        return ByteBuffer.wrap(data, offset, 4)
                .order(ByteOrder.LITTLE_ENDIAN)
                .getInt();
    }

    public static short readShortLittleEndian(byte[] data, int offset) {
        return ByteBuffer.wrap(data, offset, 2)
                .order(ByteOrder.LITTLE_ENDIAN)
                .getShort();
    }

    public static int readUnsignedShort(byte[] data, int offset) {
        return readShortLittleEndian(data, offset) & 0xFFFF;
    }

    public static long readUnsignedInt(byte[] data, int offset) {
        return readIntLittleEndian(data, offset) & 0xFFFFFFFFL;
    }

}
