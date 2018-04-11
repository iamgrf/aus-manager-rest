package com.aus.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;

public class TokenUtil {

    private final static String KEY = "%i?j/a*[spH0o,Vy{f4!dZI^";

    public static String creataToken(String sub){
        try {
            return JWT.create()
                    .withSubject(sub)
                    .sign(Algorithm.HMAC256(KEY));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String verifyToken(String token) throws RuntimeException{
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY))
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws Exception {
        String token = creataToken("dfafdsafdsafdasfdsa");
        System.out.println(token);
        System.out.println(verifyToken(token));
    }

}
