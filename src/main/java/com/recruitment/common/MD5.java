package com.recruitment.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mcholka on 2014-05-22. Enjoy!
 */
public class MD5 {
    public String getMd5(String value){
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(value.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
