package br.com.zupacademy.caico.mercadolivre.cadastroopiniaoproduto;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.util.Assert;

import br.com.zupacademy.caico.mercadolivre.cadastroprodutos.Produtos;
import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;
import br.com.zupacademy.caico.mercadolivre.validators.NaoCadastrado;

public class OpiniaoRequest {

	@NotNull @Range(min = 1, max = 5)
	private Integer nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank @Length(max = 500)
	private String descricao;

	@Deprecated
	public OpiniaoRequest() {
		
	}
	
	public OpiniaoRequest(@NotNull @Range(min = 1, max = 5) Integer nota, @NotBlank String titulo,
			@NotBlank @Length(max = 500) String descricao, @NotNull Long produto_id) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
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

	public OpiniaoProdutos toModel(@Valid OpiniaoRequest opiniaoRequest, EntityManager entityManager, Usuarios logado, Produtos produto) {
		OpiniaoProdutos opiniao = new OpiniaoProdutos(
				opiniaoRequest.getNota(),
				opiniaoRequest.getTitulo(),
				opiniaoRequest.getDescricao(),
				logado,
				produto				
				);
		
		entityManager.persist(opiniao);
		
		
		return opiniao;
	}
	
}
