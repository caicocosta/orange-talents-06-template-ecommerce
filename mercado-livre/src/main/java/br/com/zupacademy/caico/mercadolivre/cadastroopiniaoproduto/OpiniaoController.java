package br.com.zupacademy.caico.mercadolivre.cadastroopiniaoproduto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.caico.mercadolivre.cadastroprodutos.Produtos;
import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;
import br.com.zupacademy.caico.mercadolivre.exception.Exceptions;

@RestController
public class OpiniaoController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@PostMapping("produtos/{id}/opinioes")
	@Transactional
	public ResponseEntity<?> adicionaOpiniao(@RequestBody @Valid OpiniaoRequest opiniaoRequest, @PathVariable Long id ,@AuthenticationPrincipal Usuarios logado){
		Produtos produto = entityManager.find(Produtos.class, id);
		if (produto == null) {
			throw new Exceptions("Produto não cadastro no sistema!");
		}
		
		OpiniaoProdutos opiniao = opiniaoRequest.toModel(opiniaoRequest, entityManager, logado, produto);
		
		return ResponseEntity.ok().build();	
	}
}
