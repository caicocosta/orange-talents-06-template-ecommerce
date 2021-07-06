package br.com.zupacademy.caico.mercadolivre.cadastrocategoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categorias {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@ManyToOne
	private Categorias categoria;

	@Deprecated
	public Categorias() {
		
	}
	
	public Categorias(@NotBlank String nome) {
		this.nome = nome;
	}

	public Categorias getCategoria() {
		return categoria;
	}

	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
}
