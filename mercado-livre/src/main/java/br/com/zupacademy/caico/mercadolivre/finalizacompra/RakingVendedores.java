package br.com.zupacademy.caico.mercadolivre.finalizacompra;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.caico.mercadolivre.efetuacompra.Compras;

@Component
public class RakingVendedores {

	public void processarRaking(Compras compra) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("id",compra.getId(), "vendedor", compra.getProduto().getUsuario().getLogin());
		restTemplate.postForEntity("http://localhost:8080/processa-ranking", request, String.class);
	}

	
}
