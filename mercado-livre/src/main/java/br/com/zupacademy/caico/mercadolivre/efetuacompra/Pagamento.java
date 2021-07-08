package br.com.zupacademy.caico.mercadolivre.efetuacompra;

import java.util.UUID;

import org.springframework.web.util.UriComponentsBuilder;

public interface Pagamento {

	String redireciona(UUID uuidCompra, UriComponentsBuilder uri);
}
