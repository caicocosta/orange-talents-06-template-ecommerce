package br.com.zupacademy.caico.mercadolivre.finalizacompra;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.caico.mercadolivre.efetuacompra.Compras;

@Component
public class NotaFiscal {

	public void processaNota (Compras compra) {
		
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra",compra.getId(), "produto", compra.getProduto().getDescricao());
		restTemplate.postForEntity("http://localhost:8080/gera-nota", request, String.class);
	}
}
