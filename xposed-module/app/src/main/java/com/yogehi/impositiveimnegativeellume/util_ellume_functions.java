package com.yogehi.impositiveimnegativeellume;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class util_ellume_functions {

    public static byte[] addElement(byte[] bArr, byte b) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length + 1);
        copyOf[copyOf.length - 1] = b;
        return copyOf;
    }

    public static short toShort(byte[] bArr) {
        return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getShort();
    }

    public static int toInt24(byte[] bArr) {
        return toInt(addElement(bArr, (byte) 0));
    }

    public static int toInt(byte[] bArr) {
        return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    public static byte[] toBytes(int i) {
        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(i).array();
    }

    public static int getUInt32(byte[] bArr) {
        if (bArr.length != 4) {
            StringBuilder a = new StringBuilder();
            a.append(bArr.length);
            a.toString();
            Object[] objArr = new Object[0];
            return -1;
        }
        return (int) ((long) (((bArr[3] & 255) << 24) | ((bArr[0] & 255) << 0) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16)));
    }

    public static int b(byte[] bArr) {
        int yayintyay = 0;
        for (byte b : bArr) {
            for (int i = 0; i < 8; i++) {
                boolean z = ((b >> (7 - i)) & 1) == 1;
                boolean z2 = ((yayintyay >> 15) & 1) == 1;
                yayintyay <<= 1;
                if (z ^ z2) {
                    yayintyay ^= 4129;
                }
            }
        }
        yayintyay &= 65535;
        return yayintyay;
    }

}
