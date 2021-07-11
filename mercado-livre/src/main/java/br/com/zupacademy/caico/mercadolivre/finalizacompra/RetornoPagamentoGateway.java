package br.com.zupacademy.caico.mercadolivre.finalizacompra;

import br.com.zupacademy.caico.mercadolivre.efetuacompra.Compras;

public interface RetornoPagamentoGateway {
	
	Transacao toTransacao(Compras compras);
}
