package br.com.zupacademy.caico.mercadolivre.cadastrousuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
public class Usuarios implements UserDetails{

	private static final long serialVersionUID = 1L;

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
	
	@ManyToMany(fetch = FetchType.EAGER)			
	private List<Perfil> perfil = new ArrayList<>();

	@Deprecated
	public Usuarios() {
		
	}
	
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
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuarios other = (Usuarios) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfil;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
