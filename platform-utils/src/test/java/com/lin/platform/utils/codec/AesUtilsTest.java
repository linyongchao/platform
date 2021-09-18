package com.lin.platform.utils.codec;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Logger;

/**
 * @author lin
 * @date 2021/9/18 10:36
 */
public class AesUtilsTest {

    private static Logger logger = Logger.getLogger("AesUtilsTest");

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        testString();
        testFile();
    }

    private static void testString() throws GeneralSecurityException {
        String test = "test";
        String result = AesUtils.encrypt(test);
        logger.info(result);
        String src = AesUtils.decrypt(result);
        logger.info(src);
    }

    private static void testFile() throws IOException, GeneralSecurityException {
        String source = "/Users/lin/Downloads/AESUtils.java";
        String target = "/Users/lin/Downloads/AESUtils2.java";
        String source2 = "/Users/lin/Downloads/AESUtils3.java";
        AesUtils.encrypt(source, target);
        AesUtils.decrypt(target, source2);
        String md5source = Md5Utils.encrypt(new File(source));
        String md5source2 = Md5Utils.encrypt(new File(source2));
        logger.info(md5source);
        logger.info(md5source2);
    }

}
