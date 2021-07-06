package br.com.zupacademy.caico.mercadolivre.cadastrousuario;

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
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@PersistenceContext
	private EntityManager entiteManager;

	@PostMapping()
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioRequest usuarioRequest){
		
		
		Usuarios usuario = usuarioRequest.toModel(usuarioRequest);
		entiteManager.persist(usuario);
		return ResponseEntity.ok().build();
	}
}
