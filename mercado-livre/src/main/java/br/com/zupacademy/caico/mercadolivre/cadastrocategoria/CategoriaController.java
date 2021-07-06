package br.com.zupacademy.caico.mercadolivre.cadastrocategoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaRequest categoriaRequest){
		Categorias categoria = categoriaRequest.toModel(categoriaRequest, entityManager);
		return ResponseEntity.ok(categoria);
	}
}
