package br.com.zupacademy.caico.mercadolivre.efetuacompra;

import java.util.UUID;

import org.springframework.web.util.UriComponentsBuilder;

public class PayPal implements Pagamento{

	@Override
	public String redireciona(UUID uuidCompra, UriComponentsBuilder uri) {
		String urlRetorno = uri.path("/retorno-pagseguro/{uuid}")
				.buildAndExpand(uuidCompra).toString();
		
		String url = "pagseguro.com?returnId=" + uuidCompra + "&redirectUrl=" + urlRetorno;
		return url;
	}


}
