package br.com.zupacademy.caico.mercadolivre.finalizacompra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.caico.mercadolivre.efetuacompra.Compras;
import br.com.zupacademy.caico.mercadolivre.efetuacompra.GatewayPagamento;

public class RetornoPagSeguroRequest implements RetornoPagamentoGateway{

	@NotNull
	private Long idCompra;
	
	@NotNull
	private String idPagSeguro;
	
	@NotNull
	private StatusPagSeguro status;

	public RetornoPagSeguroRequest(@NotNull Long idCompra, @NotNull String idPagSeguro, @NotBlank StatusPagSeguro status) {
		super();
		this.idCompra = idCompra;
		this.idPagSeguro = idPagSeguro;
		this.status = status;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public String getIdPagSeguro() {
		return idPagSeguro;
	}

	public StatusPagSeguro getStatus() {
		return status;
	}

	@Override
	public Transacao toTransacao(Compras compras) {
		
		return new Transacao(
				compras, 
				GatewayPagamento.PAGSEGURO, 
				this.status.retornaStatusTransacao(), 
				this.idPagSeguro);
	}
	
}
