package com.pahlsoft.examples.encryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * Created by aj on 10/19/14.
 */
public class TestRSAGen {
    public static void main(String[] args) throws Exception {
        System.out.println("Result (RSA):" + getRSAKey().getPublic());
        System.out.println("Result (DH):" + getDH().getPublic());


    }

    public static KeyPair getRSAKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
        KeyPair key = keyGen.generateKeyPair();
        return key;
    }

    public static KeyPair getDH() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DiffieHellman");
        keyGen.initialize(1024);
        KeyPair key = keyGen.generateKeyPair();
        return key;
    }
}
