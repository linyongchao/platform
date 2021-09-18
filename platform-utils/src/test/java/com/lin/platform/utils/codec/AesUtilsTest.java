package com.lin.platform.utils.codec;

import java.util.logging.Logger;

/**
 * @author lin
 * @date 2021/9/18 10:36
 */
public class AesUtilsTest {

    private static Logger logger = Logger.getLogger("AesUtilsTest");

    public static void main(String[] args) throws Exception {
        String test = "test";
        String result = AesUtils.encrypt(test);
        logger.info(result);
        String src = AesUtils.decrypt(result);
        logger.info(src);
    }

}
