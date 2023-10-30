package com.aathif.its.task.util;

import com.aathif.its.task.common.AccessDeniedException;
import com.aathif.its.task.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET = "j_D]4x3{1{RZSQFx%Dp/h675m)@JJw$_f*&aTGNEK_M79$S)Ct+5{);,?qc6FZ3$";
    private static final long expiry = 60 * 60;

    public String generateAccessToken(User user) {
        long currentTimeMillis = System.currentTimeMillis();
        long expiryTime = currentTimeMillis + expiry * 1000;

        Date issuedAt = new Date(currentTimeMillis);
        Date expiryAt = new Date(expiryTime);

        Claims claims = Jwts.claims().setIssuer(user.getId().toString()).setIssuedAt(issuedAt).setExpiration(expiryAt);
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());
        claims.put("mobile", user.getMobile());

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public Claims verifyToken(String authrization) throws Exception {
        try {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authrization).getBody();
        }catch (Exception e){
            throw new AccessDeniedException("Access Denied");
        }
    }

}

