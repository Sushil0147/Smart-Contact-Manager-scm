package com.sushil.main.services;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET = "U2VjcmV0S2V5QmFzZTY0RW5jb2RlZEV4YW1wbGUhMTIz";

	public String generateJwtToken(String email) {
		
		Map<String,Object> claims = new HashMap<>();
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*5))
				.signWith(getKey(),SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getKey() {
		
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractEmail(String token) {
		
		return extractClaim(token,Claims::getSubject);
	}
	private <T> T extractClaim(String token,Function<Claims,T> claimResolver) {
		
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build().parseClaimsJws(token).getBody();
	}

	// validate from database
	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractEmail(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
    
}
