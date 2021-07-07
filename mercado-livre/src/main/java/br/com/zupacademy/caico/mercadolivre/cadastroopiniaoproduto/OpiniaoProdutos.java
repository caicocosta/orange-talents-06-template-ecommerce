package br.com.zupacademy.caico.mercadolivre.cadastroopiniaoproduto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import br.com.zupacademy.caico.mercadolivre.cadastroprodutos.Produtos;
import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;

@Entity
public class OpiniaoProdutos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @Range(min = 1, max = 5)
	private Integer nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank @Length(max = 500)
	private String descricao;
	
	@NotNull
	@ManyToOne
	private Usuarios usuario;
	
	@NotNull
	@ManyToOne
	private Produtos produto;
	
	@Deprecated
	public OpiniaoProdutos() {
		
	}

	public OpiniaoProdutos(@NotNull @Range(min = 1, max = 5) Integer nota, @NotBlank String titulo,
			@NotBlank @Length(max = 500) String descricao, @NotNull Usuarios usuario, @NotNull Produtos produto) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public Produtos getProduto() {
		return produto;
	}
	
}
