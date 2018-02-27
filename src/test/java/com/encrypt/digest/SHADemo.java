package com.encrypt.digest;

import org.apache.commons.codec.binary.Hex;
import org.apache.xerces.impl.dv.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHADemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String asd = "asd";
        System.out.println(Hex.encodeHexString(asd.getBytes()));
        System.out.println(Base64.encode(asd.getBytes("UTF-8")));
        System.out.println(getSHA256Str(asd));
        System.out.println(getSHA256Str_1(asd));
    }

    public static String getSHA256Str(String str) {
        String result = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8")) ;
            result = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getSHA256Str_1(String str) {
        String result = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8")) ;
            result = byte2Hex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String byte2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String temp = null;
        for(int i=0; i<bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if(temp.length() == 1) {
                sb.append('0');
            }
            sb.append(temp);
        }
        return sb.toString();

    }
}
