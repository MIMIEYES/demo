package com.mina;

/**
 * Created by Pierreluo on 2017/12/5.
 */
public class MinaCommon {
    public static final boolean isEncrypt = true;
    public static final boolean isCheckCertValidityReally = true;
    public static final Integer MSGLENGTH = 10;
    public static final ThreadLocal<Integer> DECODER_MSGLENGTH = new ThreadLocal<>();
    public static final String ENCODING = "UTF-8";
    public static final String yourCertAliasName = "you";
    public static final String myCertAliasName = "me";



    public static void setDecoderMsgLength(int msgLength) {
        DECODER_MSGLENGTH.set(msgLength);
    }

    public static int getDecodeMsgLength() {
        Integer msgLength = DECODER_MSGLENGTH.get();
        if(msgLength == null) {
            return 0;
        } else {
            return msgLength;
        }
    }

    public static void removeDecodeMsgLength() {
        if(DECODER_MSGLENGTH.get() != null) {
            DECODER_MSGLENGTH.remove();
        }
    }
}
