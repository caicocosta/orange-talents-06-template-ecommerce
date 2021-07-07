package br.com.zupacademy.caico.mercadolivre.cadastroprodutos;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;
import br.com.zupacademy.caico.mercadolivre.validators.ProibeCaracteristicaComNomeIgualValidator;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@InitBinder(value = "ProdutoRequest")
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
	}
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private Uploader uploaderFake;

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal Usuarios logado){
		ProdutoResponse produto = produtoRequest.toModel(produtoRequest, entityManager, logado);
		return ResponseEntity.ok(produto);
	}
	
	@PostMapping("/{id}/imagens")
	@Transactional
	public ResponseEntity<?> adicionaImagens(@PathVariable Long id, @Valid ImagensRequest imagensRequest, @AuthenticationPrincipal Usuarios logado) {
		
		Set<String> links = uploaderFake.envia(imagensRequest.getImagens());
		Produtos produto = entityManager.find(Produtos.class, id);
		
		if(!produto.pertenceAoUsuario(logado)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		produto.AssociaImagens(links);
		entityManager.merge(produto);
		
		return ResponseEntity.ok(produto);
	}
}
