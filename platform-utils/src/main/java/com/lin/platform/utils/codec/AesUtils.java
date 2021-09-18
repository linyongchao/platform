package com.lin.platform.utils.codec;

import java.security.GeneralSecurityException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密解密工具类
 *
 * @author lin
 * @date 2021/9/18 10:36
 */
public class AesUtils {

    private AesUtils() {
    }

    private static final String AES = "AES";
    /**
     * 16位偏移量
     */
    private static final String KEY_VI = "viVIviVIviVIviVI";
    /**
     * 16位秘钥
     */
    private static final String SEED = "seedSEEDseedSEED";

    private static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";

    public static String encrypt(String data) throws GeneralSecurityException {
        return Base64.getEncoder().encodeToString(encrypt(data.getBytes()));
    }

    public static String decrypt(String data) throws GeneralSecurityException {
        return new String(decrypt(Base64.getDecoder().decode(data)));
    }

    public static byte[] encrypt(byte[] data) throws GeneralSecurityException {
        return encrypt(data, KEY_VI, SEED);
    }

    public static byte[] decrypt(byte[] data) throws GeneralSecurityException {
        return decrypt(data, KEY_VI, SEED);
    }

    public static byte[] encrypt(byte[] data, String vi, String seed) throws GeneralSecurityException {
        Cipher cipher = createCipher(Cipher.ENCRYPT_MODE, vi, seed);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, String vi, String seed) throws GeneralSecurityException {
        Cipher cipher = createCipher(Cipher.DECRYPT_MODE, vi, seed);
        return cipher.doFinal(data);
    }

    private static Cipher createCipher(int mode, String vi, String seed) throws GeneralSecurityException {
        SecretKeySpec key = new SecretKeySpec(seed.getBytes(), AES);
        Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
        cipher.init(mode, key, new IvParameterSpec(vi.getBytes()));
        return cipher;
    }

}
