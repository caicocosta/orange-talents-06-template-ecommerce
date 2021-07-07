package br.com.zupacademy.caico.mercadolivre.cadastroprodutos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;
import br.com.zupacademy.caico.mercadolivre.validators.ProibeCaracteristicaComNomeIgualValidator;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
	}
	
	@PersistenceContext
	private EntityManager entityManager;

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal Usuarios logado){
		ProdutoResponse produto = produtoRequest.toModel(produtoRequest, entityManager, logado);
		return ResponseEntity.ok(produto);
	}
}
