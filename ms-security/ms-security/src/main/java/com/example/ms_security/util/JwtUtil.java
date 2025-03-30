package com.example.ms_security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private static  final String SECRET="5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public <T> T extractClaims(String token, Function<Claims,T> claimsResolver) {
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public Key getSignKey(){
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email=extractUsername(token);
        return (email.equals(userDetails.getUsername())&&!isTokenExpired(token));

    }

    public String generateToken(Authentication authentication){
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Map<String,String> claims = new HashMap<>();
        claims.put("role",userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","))
        );
        return createToken(claims,userDetails);
    }

    public String createToken(Map<String,String> claims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(SignatureAlgorithm.HS256,getSignKey())
                .compact();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
