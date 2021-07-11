package br.com.zupacademy.caico.mercadolivre.finalizacompra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RankingVedendoresRequest {

	@NotNull
	private Long id;
	
	@NotBlank
	private String vendedor;

	public Long getId() {
		return id;
	}

	public String getVendedor() {
		return vendedor;
	}
	
}
