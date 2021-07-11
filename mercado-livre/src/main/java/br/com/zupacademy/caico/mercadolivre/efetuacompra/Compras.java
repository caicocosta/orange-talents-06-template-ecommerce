package br.com.zupacademy.caico.mercadolivre.efetuacompra;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.caico.mercadolivre.cadastroprodutos.Produtos;
import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;
import br.com.zupacademy.caico.mercadolivre.exception.Exceptions;
import br.com.zupacademy.caico.mercadolivre.finalizacompra.RetornoPagamentoGateway;
import br.com.zupacademy.caico.mercadolivre.finalizacompra.Transacao;

@Entity
public class Compras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Positive
	private Integer quantidade;
	
	@NotNull
	private GatewayPagamento gatewayPagamento;
	
	@NotNull
	@ManyToOne
	private Produtos produto;
	
	@NotNull
	private Double valor;
	
	@NotNull
	@ManyToOne
	private Usuarios usuario;
	
	private StatusCompra statusCompra = StatusCompra.INICIADA;
	
	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private List<Transacao> transacoes;
	
	@CreationTimestamp
	private LocalDateTime dataCriacao;

	@Deprecated
	public Compras() {
		
	}

	public Compras(@NotNull @Positive Integer quantidade, @NotNull GatewayPagamento gatewayPagamento,
			@NotNull Produtos produto, @NotNull Double valor, @NotNull Usuarios usuario) {
		super();
		this.quantidade = quantidade;
		this.gatewayPagamento = gatewayPagamento;
		this.produto = produto;
		this.usuario = usuario;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}

	public Produtos getProduto() {
		return produto;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public StatusCompra getStatusCompra() {
		return statusCompra;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	private List<Transacao> transacoesConcluidas(){
		List<Transacao> transacoesConcluidas = this.transacoes.stream().filter(Transacao :: getStatusConcluida).collect(Collectors.toList());
		return transacoesConcluidas;
	}
	
	public void addTransacao(RetornoPagamentoGateway retornoPagamento, EntityManager entityManager) {
		Transacao novaTransacao = retornoPagamento.toTransacao(this); 
		
		if(this.transacoes.contains(novaTransacao)){
			throw new Exceptions("Já existe uma transacao");
		};
		
		
		if (!transacoesConcluidas().isEmpty()) {
			throw new Exceptions("Essa transação já foi concluída");
		}
		
		this.transacoes.add(novaTransacao);
		
	}

	public boolean compraConcluida() {
		return transacoesConcluidas().size() == 1;
	}
	
}
