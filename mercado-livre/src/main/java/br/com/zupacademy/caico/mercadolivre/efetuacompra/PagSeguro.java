package br.com.zupacademy.caico.mercadolivre.efetuacompra;

import org.springframework.web.util.UriComponentsBuilder;

public class PagSeguro implements Pagamento{

	@Override
	public String redireciona(Long idCompra, UriComponentsBuilder uri) {
		String urlRetorno = uri.path("/retorno-pagseguro/{uuid}")
							.buildAndExpand(idCompra).toString();
		
		String url = "pagseguro.com?buyerId=" + idCompra + "&redirectUrl=" + urlRetorno;
		return url;
	}

}
