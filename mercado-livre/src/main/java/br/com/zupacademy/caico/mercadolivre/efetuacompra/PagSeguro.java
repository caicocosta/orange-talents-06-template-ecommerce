package br.com.zupacademy.caico.mercadolivre.efetuacompra;

import java.util.UUID;

import org.springframework.web.util.UriComponentsBuilder;

public class PagSeguro implements Pagamento{

	@Override
	public String redireciona(UUID uuidCompra, UriComponentsBuilder uri) {
		String urlRetorno = uri.path("/retorno-pagseguro/{uuid}")
							.buildAndExpand(uuidCompra).toString();
		
		String url = "paypal.com?buyerId=" + uuidCompra + "&redirectUrl=" + urlRetorno;
		return url;
	}

}
