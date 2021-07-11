package br.com.zupacademy.caico.mercadolivre.finalizacompra;

public enum StatusPagSeguro {
	
	SUCESSO,
	ERRO;
	
	public StatusTransacao retornaStatusTransacao() {
		if(this.equals(SUCESSO)) {
			return StatusTransacao.SUCESSO;
		}
		return StatusTransacao.ERRO;
	}
}
