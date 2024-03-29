package br.com.fiap.cartao.security;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtils {
	@Value("${jwt.expiration}")
	private int expirationMinutes; 
	@Value("${jwt.secret}")
	private String secret;
	
	public String generateToken(Authentication authentication) {
		
		User user = (User) authentication.getPrincipal();
		Date dataCriacao = Date.from(LocalDateTime.now().toInstant(OffsetDateTime.now().getOffset()));
		Date dataExpiracao = Date.from(LocalDateTime.now().plusMinutes(expirationMinutes).toInstant(OffsetDateTime.now().getOffset()));
		
		return Jwts.builder()
				.setIssuer("CartaoFIAP")
				.setSubject(user.getUsername())
				.setIssuedAt(dataCriacao)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS512,	secret)
				.compact();
	}
	
	public String getUsernameFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token.replace("Bearer ", ""))
				.getBody()
		        .getSubject();
	}

}
