package com.clover.utils;

import org.springframework.util.DigestUtils;

/**
 * Created by lkl on 2017/3/21.
 */
public class EncryptUtil {

    private static final String appendString = "!@#$%^^**())lkl";


    public static String md5(String source) {

        if(source == null) {
            return null;
        }

        return "31" + DigestUtils.md5DigestAsHex(confuse(source)) + "ff";
    }

    private static byte[] confuse(String source) {
        source = source + appendString;
        byte[] bytes = source.getBytes();
        bytes[1] = (byte) (bytes[0] >> 3);
        return bytes;
    }

}
