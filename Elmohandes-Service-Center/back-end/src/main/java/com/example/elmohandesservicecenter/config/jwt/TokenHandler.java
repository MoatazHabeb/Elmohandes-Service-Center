package com.example.elmohandesservicecenter.config.jwt;

import com.example.elmohandesservicecenter.model.managermodel.Manager;
import com.example.elmohandesservicecenter.model.managermodel.Rols;
import com.example.elmohandesservicecenter.sittings.TokenConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
public class TokenHandler {


    private JwtBuilder jwtBuilder;
    public JwtParser jwtParser;

    private String secret;

    private Duration timeExpired;

    public TokenHandler(TokenConfig tokenConfig) {

        secret = tokenConfig.getSecret();
        timeExpired = tokenConfig.getTime();
        Key key =  Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        jwtBuilder = Jwts.builder().signWith(key);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();


    }


    public String createToken(Manager manager){


        for(Rols role : manager.getRols()){
            role.setManagers(null);

        }

        // build the claims

        Date issuesDate = new Date(); // current date

        Date dateOfExpired = Date.from(issuesDate.toInstant().plus(timeExpired)); // calculate the expired date starting from issueDate

        return jwtBuilder.setSubject(manager.getEmail())
                .setIssuedAt(issuesDate)
                .setExpiration(dateOfExpired)
                .claim("name", manager.getFullname())
                .claim("roles", manager.getRols())
                .compact();


    }

    public boolean validateToken(String token) {

        //  check if the token is created by the system or  not
        if (jwtParser.isSigned(token)) {

            // check if the token is valid
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            Date issue = claims.getIssuedAt();
            Date expired = claims.getExpiration();


            return expired.after(new Date()) && issue.before(expired);
        }

        return  false;
    }

    public String getSubject(String token) {

        // get the body of the claim
        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        //get subject
        return claims.getSubject();
    }
}
