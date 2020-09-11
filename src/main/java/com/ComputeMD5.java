package com;

import java.security.MessageDigest;
import java.util.Base64;

public class ComputeMD5 {
    public static String encryptPassword(String s) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(md5.digest(s.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
