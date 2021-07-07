package br.com.zupacademy.caico.mercadolivre.cadastroprodutos;

import java.util.HashSet;
import java.util.Set;

import br.com.zupacademy.caico.mercadolivre.cadastrocategoria.Categorias;
import br.com.zupacademy.caico.mercadolivre.cadastrousuario.UsuarioResponse;

public class ProdutoResponse {

	private String nome;
	private Double valor;
	private Integer quantidade;
	private String descricao;
	private Categorias categoria;
	private UsuarioResponse usuario;
	
	public ProdutoResponse(String nome, Double valor, Integer quantidade, String descricao, Categorias categoria,
			UsuarioResponse usuario) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
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
	public Categorias getCategoria() {
		return categoria;
	}
	
	public UsuarioResponse getUsuario() {
		return usuario;
	}
	
}
