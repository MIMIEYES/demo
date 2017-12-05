package com.rsa;

import com.mina.MinaCommon;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.ArrayUtils;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.mina.MinaCommon.ENCODING;

/**
 * Created by Pierreluo on 2017/12/5.
 */
public class RSAUtil {

    private static final String Padding = "RSA/ECB/PKCS1Padding";
    private static KeyStore keyStore = null;
    private static PublicKey publicKey = null;
    private static PrivateKey privateKey = null;
    private static char[] storePwd = null;

    static {
        try {
            System.out.println("Initialize KeyStore start.");
            RSAUtil rsa = new RSAUtil();
            storePwd = rsa.initStorePwd();
            keyStore = rsa.initKeyStore();
            publicKey = rsa.initPublicKey();
            privateKey = rsa.initPrivateKey();
            rsa = null;
            System.out.println("Initialize KeyStore end.");
        } catch (Exception e) {
            System.err.println("Failed to Initialize KeyStore." + e.getMessage());
        }
    }

    private static KeyStore loadKeyStore() {
        return keyStore;
    }

    private static PublicKey getPublicKey() {
        return publicKey;
    }

    private static PrivateKey getPrivateKey() {
        return privateKey;
    }

    private static char[] loadStorePwd() {
        return storePwd;
    }

    public static String encrypt(String msg, String encoding) throws Exception {
        System.out.println("encrypt start with msg [" + msg + "]");
        String encryptedStr = null;
        final Cipher ci = Cipher.getInstance(Padding);
        RSAPublicKey key = (RSAPublicKey) getPublicKey();
        ci.init(Cipher.ENCRYPT_MODE, key);
        int maxLength = key.getModulus().bitLength() / 8 -11;
        msg = URLEncoder.encode(msg, ENCODING);
        byte[] bytes = msg.getBytes(ENCODING);
        int length = bytes.length;
        int offset = 0, len = 0;
        byte[] tmp;
        while (offset < length) {
            len = 0;
            if(length - offset > maxLength) {
                len = maxLength;
            } else {
                len = length - offset;
            }
            tmp = ci.doFinal(ArrayUtils.subarray(bytes, offset, offset + len));
            if(encryptedStr == null) {
                encryptedStr = new String(Hex.encodeHex(tmp));
            } else {
                encryptedStr += new String(Hex.encodeHex(tmp));
            }
            offset += len;
        }
        System.out.println("encrypt end with msg [" + encryptedStr + "]");
        return encryptedStr;
    }

    public static String decrypt(String msg, String encoding) throws Exception {
        System.out.println("decrypt start with encrypted msg [" + msg + "]");
        String decryptedStr = null;
        final Cipher ci = Cipher.getInstance(Padding);
        RSAPrivateKey key = (RSAPrivateKey) getPrivateKey();
        ci.init(Cipher.DECRYPT_MODE, key);
        int maxLength = key.getModulus().bitLength() / 8;
        byte[] bytes = Hex.decodeHex(msg.toCharArray());
        int length = bytes.length;
        int offset = 0, len = 0;
        byte[] tmp;
        while (offset < length) {
            len = 0;
            if(length - offset > maxLength) {
                len = maxLength;
            } else {
                len = length - offset;
            }
            tmp = ci.doFinal(ArrayUtils.subarray(bytes, offset, offset + len));
            if(decryptedStr == null) {
                decryptedStr = new String (tmp, ENCODING);
            } else {
                decryptedStr += new String (tmp, ENCODING);
            }
            offset += len;
        }
        decryptedStr = URLDecoder.decode(decryptedStr, ENCODING);
        System.out.println("decrypt end with msg [" + decryptedStr + "]");

        return decryptedStr;
    }

    private static  char[] loadKeyPasswd() throws Exception {
        return loadStorePwd();
    }

    private char[] initStorePwd() throws Exception {
        String pwd = "sdgasSDFSDF$123";
        char[] charPwd = pwd.toCharArray();
        return charPwd;
    }

    private KeyStore initKeyStore() throws Exception {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType()) ;
        String ksFilePath = "/home/testkeystore";
        FileInputStream fis = new FileInputStream(ksFilePath);
        char[] passwd = loadStorePwd();
        ks.load(fis, passwd);
        fis.close();
        return ks;
    }

    private PublicKey initPublicKey() throws Exception {
        KeyStore ks = loadKeyStore();
        if(MinaCommon.isCheckCertValidityReally) {
            checkValidity(ks, MinaCommon.yourCertAliasName);
        }
        Certificate cert = ks.getCertificate(MinaCommon.yourCertAliasName);
        PublicKey publicKey = cert.getPublicKey();
        return publicKey;
    }

    private PrivateKey initPrivateKey() throws Exception {
        KeyStore ks = loadKeyStore();
        if(MinaCommon.isCheckCertValidityReally) {
            checkValidity(ks, MinaCommon.myCertAliasName);
        }
        char[] passwd = loadKeyPasswd();
        Key key = ks.getKey(MinaCommon.myCertAliasName, passwd);
        return (PrivateKey) key;
    }

    private void checkValidity(KeyStore ks, String certAliasName) throws Exception {
        Certificate cert = ks.getCertificate(certAliasName);
        X509Certificate x509Certificate = (X509Certificate) cert;
        Date timeNow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // check starting date
        Date sdate = x509Certificate.getNotBefore();
        boolean isValidStarting = checkStartingDate(timeNow, sdate);
        if(!isValidStarting) {
            System.err.println("its too early about getting certificate. The certificate starts from " + sdf.format(x509Certificate.getNotBefore()) + ", and now " + sdf.format(timeNow));
            throw new CertificateNotYetValidException();
        }

        // check ending date
        Date edate = x509Certificate.getNotAfter();
        boolean isValidEnding = checkEndingDate(timeNow, edate, 0);
        if(!isValidEnding) {
            System.err.println("The certificate has expired. The period of validity has ended of " + sdf.format(x509Certificate.getNotAfter()) + ", and now " + sdf.format(timeNow));
            throw new CertificateExpiredException();
        }
    }

    private static boolean checkValidityEndingBefore1M(String certAliasName) throws Exception {
        KeyStore ks = loadKeyStore();
        Certificate cert = ks.getCertificate(certAliasName);
        X509Certificate x509Certificate = (X509Certificate) cert;
        Date timeNow = new Date();
        Date edate = x509Certificate.getNotAfter();
        boolean isValid = checkEndingDate(timeNow, edate, -1);
        if(!isValid) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.err.println("Certificate will expire next month. The period of validity ends to " + sdf.format(edate) + ", and now " + sdf.format(timeNow));
        }
        return isValid;
    }

    private static boolean checkEndingDate(Date timeNow, Date edate, int invalidMonth) {
        boolean isValid = true;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(edate);
        calendar.add(Calendar.MONTH, invalidMonth);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if(!timeNow.before(calendar.getTime())) {
            isValid = false;
        }
        return isValid;
    }

    private static boolean checkStartingDate(Date timeNow, Date sdate) {
        boolean isValid = true;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if(timeNow.before(calendar.getTime())) {
            isValid = false;
        }
        return isValid;
    }
}
