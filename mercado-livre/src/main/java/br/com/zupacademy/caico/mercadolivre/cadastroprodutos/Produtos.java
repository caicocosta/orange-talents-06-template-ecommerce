package br.com.zupacademy.caico.mercadolivre.cadastroprodutos;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import br.com.zupacademy.caico.mercadolivre.cadastrocategoria.Categorias;
import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;

@Entity
public class Produtos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull @Positive
	private Double valor;
	
	@NotNull @PositiveOrZero
	private Integer quantidade;
	
	//@NotNull @Size(min = 3)
	//private List<Caracteristicas> caracteristicas;
	
	@NotBlank @Length(max = 1000)
	private String descricao;
	
	@NotNull
	@ManyToOne 
	private Categorias categoria;
	
	@ManyToOne
	private Usuarios usuario;
	
	@CreationTimestamp
	private LocalDateTime dataCriacao;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicasProduto> caracteristicas = new HashSet<>();
	

	/**
	 * 
	 * @param nome
	 * @param valor
	 * @param quantidade
	 * @param descricao
	 * @param categoria
	 */
	public Produtos(@NotBlank String nome, @NotNull @Positive Double valor, @NotNull @PositiveOrZero Integer quantidade,
			@NotBlank @Length(max = 1000) String descricao, Usuarios usuario, @NotNull Categorias categoria, Collection<CaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
		Set<CaracteristicasProduto> novasCaracteristicas = caracteristicas.stream()
																.map(caracteristica -> caracteristica.toModel(this))
																.collect(Collectors.toSet());
		this.caracteristicas.addAll(novasCaracteristicas);
		
		Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no mínimo 3 ou mais características.");
	}


	public Long getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	public Double getValor() {
		return valor;
	}


	public Integer getQuantidade() {
		return quantidade;
	}


	public String getDescricao() {
		return descricao;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public Categorias getCategoria() {
		return categoria;
	}


	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	
	public Set<CaracteristicasProduto> getCaracteristicas() {
		return caracteristicas;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Produtos other = (Produtos) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
