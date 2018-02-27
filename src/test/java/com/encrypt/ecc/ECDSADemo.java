
package com.encrypt.ecc;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class ECDSADemo {
    private static String src = "ecdsa security";
    public static void main(String[] args) {
        jdkECDSA();
    }

    public static void jdkECDSA(){
        try {
            //1.初始化密钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(256);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            ECPublicKey ecPublicKey = (ECPublicKey)keyPair.getPublic();
            ECPrivateKey ecPrivateKey = (ECPrivateKey)keyPair.getPrivate();


            //2.执行签名
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());

            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initSign(privateKey);
            signature.update(src.getBytes("UTF-8"));
            byte[] res = signature.sign();
            System.out.println("签名："+HexBin.encode(res));

            //3.验证签名
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("EC");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            signature = Signature.getInstance("SHA256withECDSA");
            signature.initVerify(publicKey);
            signature.update(src.getBytes("UTF-8"));
            boolean bool = signature.verify(res);
            System.out.println("验证："+bool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
