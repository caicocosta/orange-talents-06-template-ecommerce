package br.com.zupacademy.caico.mercadolivre.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${mercadolivre.jwt.expiration}")
	private String expiration;
	
	@Value("${mercadolivre.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authentication) {
		Usuarios logado = (Usuarios) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiration = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API - Fórum Alura")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiration)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
		
	}

	public Long getIdUsuario(String token) {
		Claims claim = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claim.getSubject());
	}

	
}
