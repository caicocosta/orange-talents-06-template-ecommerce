package br.com.zupacademy.caico.mercadolivre.detalhaproduto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.caico.mercadolivre.cadastroprodutos.Produtos;
import br.com.zupacademy.caico.mercadolivre.exception.Exceptions;

@RestController
public class DetalheProdutoController {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@GetMapping("/produtos/{id}")
	public ResponseEntity<DetalheProdutoResponse> detalharProduto(@PathVariable Long id){
		Produtos produto = entityManager.find(Produtos.class, id);
		if (produto == null) {
			throw new Exceptions("Produto não cadastro no sistema!");
		}
		
		DetalheProdutoResponse detalheProduto = new DetalheProdutoResponse(entityManager, produto);
		return ResponseEntity.ok(detalheProduto);
		
		
	}
	
	
}

