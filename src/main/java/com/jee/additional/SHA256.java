package com.jee.additional;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

    public static String sha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(value.getBytes("UTF-8"));

            return convertByteArrayToHexString(hashedBytes);

        } catch (NoSuchAlgorithmException nsae) {
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return  null;
        }

    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }

}
