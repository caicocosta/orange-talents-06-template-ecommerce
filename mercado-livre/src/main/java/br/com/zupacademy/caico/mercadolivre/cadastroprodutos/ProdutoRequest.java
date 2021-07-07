package br.com.zupacademy.caico.mercadolivre.cadastroprodutos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import com.sun.istack.NotNull;

import br.com.zupacademy.caico.mercadolivre.cadastrocategoria.Categorias;
import br.com.zupacademy.caico.mercadolivre.cadastrousuario.UsuarioResponse;
import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;
import br.com.zupacademy.caico.mercadolivre.validators.NaoCadastrado;

public class ProdutoRequest {

	@NotBlank
	private String nome;
	
	@NotNull @Positive
	private Double valor;
	
	@NotNull @PositiveOrZero
	private Integer quantidade;

	@NotBlank @Length(max = 1000)
	private String descricao;
	
	@NotNull @NaoCadastrado(domainClass = Categorias.class, fieldName = "id", message = "Categoria não cadastrada no sistema!")
	private Long categoria_id;
	
	@Size(min = 3)
	@Valid
	private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();

	public ProdutoRequest(@NotBlank String nome, @Positive Double valor, @PositiveOrZero Integer quantidade,
			@NotBlank @Length(max = 1000) String descricao, Long categoria_id, @Size(min = 3)
			@Valid
			List<CaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria_id = categoria_id;
		this.caracteristicas.addAll(caracteristicas);
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

	public Long getCategoria_id() {
		return categoria_id;
	}
	
	public List<CaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}

	public ProdutoResponse toModel(@Valid ProdutoRequest produtoRequest, EntityManager entityManager, Usuarios usuario) {
		Categorias categoria = entityManager.find(Categorias.class, produtoRequest.getCategoria_id());
		Assert.state(categoria != null, "Categoria não cadastrada");
		
		UsuarioResponse usuarioResponse = new UsuarioResponse(usuario.getId(), usuario.getUsername());

		Produtos produto = new Produtos(
					produtoRequest.getNome(),
					produtoRequest.getValor(),
					produtoRequest.getQuantidade(),
					produtoRequest.getDescricao(),
					usuario,
					categoria,
					caracteristicas
				);
		

		entityManager.persist(produto);
		
		ProdutoResponse produtoResponse = new ProdutoResponse(
				produto.getNome(),
				produto.getValor(),
				produto.getQuantidade(),
				produto.getDescricao(),
				produto.getCategoria(),
				produto.getCaracteristicas(),
				usuarioResponse
				);
		
		return produtoResponse;
	}

	public Set<String> buscarCaracteristicasIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();
		for(CaracteristicaRequest caracteristica: caracteristicas) {
			String nome = caracteristica.getNome();
			if(!nomesIguais.add(nome)) {
				resultados.add(nome);	
			}				
		}
		
		return resultados;
	}
	
}
