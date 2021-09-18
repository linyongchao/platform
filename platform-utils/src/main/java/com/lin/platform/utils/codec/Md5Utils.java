package com.lin.platform.utils.codec;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * md5工具类
 */
public class Md5Utils {

    private Md5Utils() {
    }

    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * Encodes a string
     *
     * @param str String to encode
     * @return Encoded String
     */
    public static String encrypt(String str) {
        return new String(encrypt(str.getBytes()));
    }

    /**
     * encode bytes
     *
     * @param source source bytes
     * @return the encode String
     */
    public static char[] encrypt(byte[] source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            byte[] tmp = md.digest();
            char[] str = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return str;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Encodes a file
     *
     * @param file File to encode
     * @return Encoded String
     */
    public static String encrypt(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        try (FileInputStream in = new FileInputStream(file)) {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[8192];
            int length;
            while ((length = in.read(buffer)) != -1) {
                messagedigest.update(buffer, 0, length);
            }
            return bufferToHex(messagedigest.digest()).toLowerCase();
        } catch (Exception e) {
            return null;
        }
    }

    private static String bufferToHex(byte[] bytes) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte[] bytes, int m, int n) {
        StringBuilder stringbuffer = new StringBuilder(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuilder stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

}
