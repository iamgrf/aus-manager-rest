package com.aus.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PasswordUtil {

    public static final Integer ITERATIONS = 50000;
    public static final Integer DERIVEDKEYLENGTH = 256;
    public static final String ALG = "PBKDF2WithHmacSHA256";

    public static byte[] encrypted(String password, byte[] salt,  int iterations,  int derivedKeyLength, String alg) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance(alg);
        return f.generateSecret(spec).getEncoded();
    }

    public static String encryptedToString(String password, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return HexBin.encode(encrypted(password, salt, ITERATIONS, DERIVEDKEYLENGTH, ALG));
    }

    public static String encryptedToString(String password, String salt) {
        try {
            return HexBin.encode(encrypted(password, salt.getBytes(), ITERATIONS, DERIVEDKEYLENGTH, ALG));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
        System.out.println(encryptedToString(StringUtil.md5("123456"), "admin").toLowerCase());
    }


}
