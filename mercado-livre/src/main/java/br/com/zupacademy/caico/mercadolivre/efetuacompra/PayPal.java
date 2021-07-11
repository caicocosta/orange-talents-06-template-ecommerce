package br.com.zupacademy.caico.mercadolivre.efetuacompra;

import org.springframework.web.util.UriComponentsBuilder;

public class PayPal implements Pagamento{

	@Override
	public String redireciona(Long idCompra, UriComponentsBuilder uri) {
		String urlRetorno = uri.path("/retorno-pagseguro/{uuid}")
				.buildAndExpand(idCompra).toString();
		
		String url = "paypal.com?returnId=" + idCompra + "&redirectUrl=" + urlRetorno;
		return url;
	}


}
