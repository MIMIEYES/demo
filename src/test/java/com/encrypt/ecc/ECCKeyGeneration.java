package com.encrypt.ecc;

import org.apache.xerces.impl.dv.util.HexBin;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class ECCKeyGeneration {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecsp = new ECGenParameterSpec("secp256k1");
        kpg.initialize(ecsp);

        KeyPair kp = kpg.genKeyPair();
        PrivateKey privateKey = kp.getPrivate();
        PublicKey publicKey = kp.getPublic();

        Signature ecdsa = Signature.getInstance("SHA256withECDSA");
        ecdsa.initSign(privateKey);

        String text = "test signature";
        System.out.println("Text: " + text);
        byte[] src = text.getBytes("UTF-8");

        ecdsa.update(src);
        byte[] bytesSignature = ecdsa.sign();
        System.out.println("Signature str: " + HexBin.encode(bytesSignature));

        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initVerify(publicKey);
        signature.update(src);
        boolean result = signature.verify(bytesSignature);
        System.out.println("Valid:" + result);
    }

}
