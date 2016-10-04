package com.gnut.utils;

import org.springframework.util.DigestUtils;

/**
 * Created by guitarnut on 9/24/16.
 */
public class SimpleMD5Encoder {

    public static String encode(String seed) {
        return DigestUtils.md5DigestAsHex(seed.getBytes());
    }

}