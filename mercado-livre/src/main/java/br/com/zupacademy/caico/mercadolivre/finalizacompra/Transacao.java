package br.com.zupacademy.caico.mercadolivre.finalizacompra;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.caico.mercadolivre.efetuacompra.Compras;
import br.com.zupacademy.caico.mercadolivre.efetuacompra.GatewayPagamento;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Compras compra;
	
	@NotNull
	private GatewayPagamento gateway;
	
	@NotNull
	private String idTransacaoGateway;
	
	private StatusTransacao status;

	@CreationTimestamp
	private LocalDateTime dataCriacao;
	
	@Deprecated
	public Transacao() {
		
	}
	
	public Transacao(Compras compra, @NotNull GatewayPagamento gateway, StatusTransacao status ,@NotNull String idTransacaoGateway) {
		super();
		this.compra = compra;
		this.gateway = gateway;
		this.idTransacaoGateway = idTransacaoGateway;
		this.status = status;
	}
	
	public boolean getStatusConcluida() {
		return this.status.equals(StatusTransacao.SUCESSO);
	}
}
