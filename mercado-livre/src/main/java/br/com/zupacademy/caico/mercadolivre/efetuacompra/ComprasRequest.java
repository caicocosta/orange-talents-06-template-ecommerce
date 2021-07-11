package br.com.zupacademy.caico.mercadolivre.efetuacompra;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.caico.mercadolivre.cadastroprodutos.MovimentacaoEstoque;
import br.com.zupacademy.caico.mercadolivre.cadastroprodutos.Produtos;
import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;
import br.com.zupacademy.caico.mercadolivre.exception.Exceptions;

public class ComprasRequest {

	@NotNull
	private Long produto_id;
	
	@NotNull
	@Positive
	private Integer quantidade;
	
	@NotNull
	private GatewayPagamento gatewayPagamento;

	public ComprasRequest(@NotNull @Positive Integer quantidade, @NotNull GatewayPagamento gatewayPagamento) {
		super();
		this.quantidade = quantidade;
		this.gatewayPagamento = gatewayPagamento;
	}
	
	public Long getProduto_id() {
		return produto_id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}

	public String toModel(@Valid ComprasRequest comprasRequest, EntityManager entityManager, Produtos produto,
			Usuarios usuario, Pagamento pagamento, UriComponentsBuilder uri) {
	
		Compras compra = new Compras(
				this.quantidade, 
				this.gatewayPagamento, 
				produto, 
				produto.getValor(),
				usuario);

		if (!produto.diminueEstoque(this.quantidade)) {
			throw new Exceptions("Houve um erro na sua compra, o produto não está mais em estoque.");
		};
		
		entityManager.persist(compra);
	    String urlRetorno =	pagamento.redireciona(compra.getId(), uri);
		return urlRetorno;
	}
	
}
