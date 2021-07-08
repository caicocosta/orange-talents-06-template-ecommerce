package br.com.zupacademy.caico.mercadolivre.cadastroprodutos;

public class DetalheProdutoCaracteristica {

	private String nome;
	private String descricao;
	public DetalheProdutoCaracteristica(CaracteristicasProduto caracteristica) {
		super();
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
