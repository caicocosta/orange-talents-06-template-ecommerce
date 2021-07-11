package br.com.zupacademy.caico.mercadolivre.finalizacompra;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessaCompraController {

	@PostMapping("/gera-nota")
	public void gerarNota(@RequestBody NotaFiscalRequest notaFiscalRequest){
		System.out.println("Nota Fisal");
		System.out.println("Compra : " + notaFiscalRequest.getIdCompra());
		System.out.println("Produto: " + notaFiscalRequest.getProduto());
	}
	
	@PostMapping("/processa-ranking")
	public void processaRanking(@RequestBody RankingVedendoresRequest ranking) {
		
		System.out.println("Novo Ranking");
		System.out.println("Compra : " + ranking.getId());
		System.out.println("Vendedor: " + ranking.getVendedor());
		
	}
}
