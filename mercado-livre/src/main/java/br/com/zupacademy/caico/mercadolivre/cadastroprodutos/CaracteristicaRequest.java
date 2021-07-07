package br.com.zupacademy.caico.mercadolivre.cadastroprodutos;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicaRequest {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	public CaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public CaracteristicasProduto toModel(@NotNull @Valid Produtos produto) {
		return new CaracteristicasProduto(nome, descricao, produto);
	}
}

