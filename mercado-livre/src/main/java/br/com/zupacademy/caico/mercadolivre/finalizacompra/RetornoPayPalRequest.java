package br.com.zupacademy.caico.mercadolivre.finalizacompra;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import br.com.zupacademy.caico.mercadolivre.efetuacompra.Compras;
import br.com.zupacademy.caico.mercadolivre.efetuacompra.GatewayPagamento;

public class RetornoPayPalRequest implements RetornoPagamentoGateway{

	@NotNull
	private Long idCompra;
	
	@NotNull
	private String idPayPal;
	
	@NotNull
	@Range(min = 0, max = 1)
	private Integer status;
	
	public RetornoPayPalRequest(@NotNull Long idCompra, @NotNull String idPayPal,
			@NotNull @Range(min = 0, max = 1) Integer status) {
		super();
		this.idCompra = idCompra;
		this.idPayPal = idPayPal;
		this.status = status;
	}
	
	public Long getIdCompra() {
		return idCompra;
	}

	@Override
	public Transacao toTransacao(Compras compras) {
		System.out.println("Aqui!!");
		
		return new Transacao(
				compras,
				GatewayPagamento.PAYPAL,
				this.status == 1 ? StatusTransacao.SUCESSO : StatusTransacao.ERRO,
				this.idPayPal		
				);
	}

}
