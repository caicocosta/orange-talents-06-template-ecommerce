package br.com.zupacademy.caico.mercadolivre.finalizacompra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {

	@NotNull
	private Long idCompra;
	@NotBlank
	private String produto;

	public Long getIdCompra() {
		return idCompra;
	}
	public String getProduto() {
		return produto;
	}
	
}
