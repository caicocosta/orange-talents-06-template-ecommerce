package br.com.zupacademy.caico.mercadolivre.cadastrousuario;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;


public class SenhaLimpa {

	private String senha;

	public SenhaLimpa(@NotBlank @Length(min = 6)String senha) {
		Assert.hasLength(senha, "A senha não pode ser vazia!");
		Assert.isTrue(senha.length() >= 6, "A senha precisa ser maior ou igual à 6 caracteres.");
		this.senha = senha;
	}
	

	public String hash() {
		return new BCryptPasswordEncoder().encode(senha);
	}
}
