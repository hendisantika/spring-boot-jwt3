package com.hendisantika.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import java.util.Base64;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jwt3
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/9/22
 * Time: 12:02
 * To change this template use File | Settings | File Templates.
 */
class JWTUtilTest {
    private static final String secretKey = "naruto";

    @Test
    public void generateTokenTest() {
        // code to test generated Token
        String token = JWTUtil.generateToken("Token1");
        System.out.println("------------------------TOKEN----------------------------------------------------");
        System.out.println(token);
        System.out.println();
        System.out.println("------------------------CLAIMS----------------------------------------------------");

        //code to test parsed token : Claims

        Claims claims = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()))
                .parseClaimsJws(token)
                .getBody();

        System.out.println("Token ID: " + claims.getId());
        System.out.println("Token Subject: " + claims.getSubject());
        System.out.println("Token Issuer: " + claims.getIssuer());
        System.out.println("Token Issue Date: " + claims.getIssuedAt());
        System.out.println("Token Expiration Date: " + claims.getExpiration());
        System.out.println("Token Audience: " + claims.getAudience());
    }
}