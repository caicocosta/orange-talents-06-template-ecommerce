package br.com.zupacademy.caico.mercadolivre.cadastroperguntas;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
public class PerguntasController {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private Email emailFake;
	
	
	@PostMapping("/produtos/{id}/perguntas")
	@Transactional
	public ResponseEntity<?> adicionaPergunta(@RequestBody @Valid PerguntaRequest perguntaRequest, @PathVariable Long id, @AuthenticationPrincipal Usuarios logado){
		Produtos produto = entityManager.find(Produtos.class, id);
		if (produto == null) {
			throw new Exceptions("Produto não cadastro no sistema!");
		}
		
		PerguntasProdutos pergunta = perguntaRequest.toModel(entityManager, produto, logado);
		String assunto = "Pergunta sobre o produto: " + pergunta.getProduto().getNome()
				          + pergunta.getTitulo();
		
		emailFake.Envia(pergunta.getUsuario().getLogin(), "vendedor@vendedor.com", assunto);
		
		return ResponseEntity.ok().build();
	}
}
