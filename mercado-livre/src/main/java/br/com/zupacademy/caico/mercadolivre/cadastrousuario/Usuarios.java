package br.com.zupacademy.caico.mercadolivre.cadastrousuario;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
public class Usuarios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank @Email
	@Column(nullable = false)
	private String login;
	
	@NotBlank @Length(min = 6)
	@Column(nullable = false)
	private String senha;
	
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime dataCriacao;

	/**
	 * 
	 * @param login Tem que ser no formato de um e-mail.
	 * @param senhaLimpa Não pode ser vazia ou nula.
	 */
	public Usuarios(@NotBlank @Email String login, SenhaLimpa senhaLimpa) {
		Assert.isTrue(StringUtils.hasLength(login), "O login não pode ser nullo.");
		Assert.notNull(senhaLimpa, "O objeto do tipo senha, não pode ser nulo.");
		
		this.login = login;
		this.senha = senhaLimpa.hash();
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	
}
