package br.com.zupacademy.caico.mercadolivre.cadastrousuario;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class UsuarioRequest {

	@NotBlank @Email
	private String login;
	
	@NotBlank @Length(min = 6)
	private String senha;

	public UsuarioRequest(@NotBlank @Email String login, @NotBlank @Min(6) String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public Usuarios toModel(@Valid UsuarioRequest usuarioRequest) {
		Usuarios usuario = new Usuarios(usuarioRequest.getLogin(), new SenhaLimpa(usuarioRequest.getSenha()));
		return usuario;
	}

	
	
}
