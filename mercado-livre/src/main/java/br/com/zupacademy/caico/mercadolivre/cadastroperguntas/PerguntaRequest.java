package br.com.zupacademy.caico.mercadolivre.cadastroperguntas;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.caico.mercadolivre.cadastroprodutos.Produtos;
import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;

public class PerguntaRequest {

	@NotBlank
	private String titulo;

	@Deprecated
	public PerguntaRequest() {
		
	}
	
	public PerguntaRequest(@NotBlank String titulo) {
		super();
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public PerguntasProdutos toModel(EntityManager entityManager, Produtos produto, Usuarios logado) {
		
		PerguntasProdutos pergunta = new PerguntasProdutos(
					this.titulo,
					produto, 
					logado
				);
		
		entityManager.persist(pergunta);
		
		return pergunta;
	}
	
	
}
