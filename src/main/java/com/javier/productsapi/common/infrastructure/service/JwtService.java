package com.javier.productsapi.common.infrastructure.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // @Value("${jwt.secret}") //desarollo solo sino ENV
    private static final String SECRET_KEY = "QWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXo0NTY3ODkwMTIzNDU2Nzg5MDEyMw==";
    private static final long TOKEN_EXPIRATION = 1000 * 60 * 60 * 24; // 1 dia


    public String generateToken(UserDetails userDetails) { // roles del usuario en el map

        Map<String, Object> claims = Map.of("authorities", userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toArray(String[]::new));

        return generateToken(claims, userDetails.getUsername());


    }


    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact()
                ;

    }

    private Key getSignKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getAllClaims(String token) {

        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }


    }

    private <T> T getClaim(String token, Function<Claims, T> claimsMapper) {

        Claims allClaims = getAllClaims(token);
        return claimsMapper.apply(allClaims);

    }

    public String getUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public String getAuthorities(String token) {
        return getClaim(token, claims -> claims.get("authorities").toString());
    }


}
