package br.com.zupacademy.caico.mercadolivre.detalhaproduto;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.zupacademy.caico.mercadolivre.cadastroprodutos.DetalheProdutoCaracteristica;
import br.com.zupacademy.caico.mercadolivre.cadastroprodutos.ImagemProduto;
import br.com.zupacademy.caico.mercadolivre.cadastroprodutos.Produtos;

public class DetalheProdutoResponse {

	private Set<ImagemProduto> imagens;
	private String nome;
	private Double valor;
	private Set<DetalheProdutoCaracteristica> caracteristicas;
	private String descricao;
	private Double mediaNotas;
	private Integer totalNotas;
	private List<OpiniaoResponse> opinioes;
	private List<PerguntaResponse> perguntas;
	
	@Deprecated
	public DetalheProdutoResponse() {
		
	}
	
	public DetalheProdutoResponse(EntityManager entityManager, Produtos produto) {
		
		this.imagens = produto.getImagens();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.caracteristicas = produto.litaCaracteristicas(DetalheProdutoCaracteristica::new);
		this.descricao = produto.getDescricao();
		this.opinioes = listaOpinioes(entityManager, produto.getId()); 
		this.mediaNotas = calculaMediaOpinioes(this.opinioes);
		this.totalNotas = this.opinioes.size();
		this.perguntas = listaPeguntas(entityManager, produto.getId());
		
	}

	private Double calculaMediaOpinioes(List<OpiniaoResponse> opinioes) {
		Double media = 0.0;
		Double soma = 0.0;
		for (OpiniaoResponse opiniaoResponse : opinioes) {
			soma = soma + opiniaoResponse.getNota(); 
		}
		
		media = soma / opinioes.size();
		
		return media;
	}

	private List<OpiniaoResponse> listaOpinioes(EntityManager entityManager, Long id) {
		OpiniaoResponse opiniao = new OpiniaoResponse();
		List<OpiniaoResponse> opinioesDoProduto = opiniao.listarOpinioes(entityManager, id);
		return opinioesDoProduto;
	}

	private List<PerguntaResponse> listaPeguntas(EntityManager entityManager, Long id) {
		PerguntaResponse pergunta = new PerguntaResponse();
		List<PerguntaResponse> perguntasDoProduto = pergunta.listarPerguntas(entityManager, id);
		return perguntasDoProduto;
	}
	
	public Set<ImagemProduto> getImagens() {
		return imagens;
	}


	public String getNome() {
		return nome;
	}

	public Double getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getMediaNotas() {
		return mediaNotas;
	}

	public Integer getTotalNotas() {
		return totalNotas;
	}


	public List<OpiniaoResponse> getOpinioes() {
		return opinioes;
	}

	public List<PerguntaResponse> getPerguntas() {
		return perguntas;
	}
	
	public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

}
