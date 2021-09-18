package com.lin.platform.utils.codec;

import java.util.logging.Logger;

/**
 * @author lin
 * @date 2021/9/18 14:15
 */
public class Md5UtilsTest {

    private static Logger logger = Logger.getLogger("Md5UtilsTest");

    public static void main(String[] args) {
        testString();
    }

    private static void testString() {
        String test = "test";
        String result = Md5Utils.encrypt(test);
        logger.info(result);
        logger.info("098f6bcd4621d373cade4e832627b4f6");
    }
}
