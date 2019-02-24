package com.example.blindtest.BDD;

import java.security.MessageDigest;
//permet le hashage des mots de passe
public class md5 {
    public static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return md5.digest();
    }
}
