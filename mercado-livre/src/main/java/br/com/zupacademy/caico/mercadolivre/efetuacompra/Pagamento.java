package br.com.zupacademy.caico.mercadolivre.efetuacompra;

import org.springframework.web.util.UriComponentsBuilder;

public interface Pagamento {

	String redireciona(Long idCompra, UriComponentsBuilder uri);
	
}
