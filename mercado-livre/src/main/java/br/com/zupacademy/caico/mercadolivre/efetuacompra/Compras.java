package br.com.zupacademy.caico.mercadolivre.efetuacompra;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.caico.mercadolivre.cadastroprodutos.Produtos;
import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;

@Entity
public class Compras {

	@Id
	private UUID uuid;
	
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
	
	@CreationTimestamp
	private LocalDateTime dataCriacao;
	
	@PrePersist
	public void prePersist() {
	    this.uuid = UUID.randomUUID();
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

	public UUID getUuid() {
		return uuid;
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

	
}
