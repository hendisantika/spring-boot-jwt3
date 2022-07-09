package com.hendisantika.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jwt3
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/9/22
 * Time: 12:01
 * To change this template use File | Settings | File Templates.
 */
@Component
public class JWTUtil {
    @Value("${app.secret.key}")
    private static String secretKey;

    // code to generate Token
    public static String generateToken(String subject) {
        String tokenId = String.valueOf(new Random().nextInt(10000));
        return Jwts.builder()
                .setId(tokenId)
                .setSubject(subject)
                .setIssuer("ABC_Ltd")
                .setAudience("XYZ_Ltd")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(secretKey.getBytes()))
                .compact();
    }

    //code to get Claims
    public static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }

    // code to check if token is valid
    public boolean isValidToken(String token) {
        return getClaims(token).getExpiration().after(new Date(System.currentTimeMillis()));
    }

    // code to check if token is valid as per username
    public boolean isValidToken(String token, String username) {
        String tokenUserName = getSubject(token);
        return (username.equals(tokenUserName) && !isTokenExpired(token));
    }

    // code to check if token is expired
    public boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date(System.currentTimeMillis()));
    }
}
