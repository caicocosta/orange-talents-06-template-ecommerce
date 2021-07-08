package br.com.zupacademy.caico.mercadolivre.detalhaproduto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;

public class PerguntaResponse {
	
	private String titulo;
	private String usuario;
	
	@Deprecated
	public PerguntaResponse() {
	}
	
	public PerguntaResponse(String titulo, Usuarios usuario) {
		this.titulo = titulo;
		this.usuario = usuario.getLogin();
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public List<PerguntaResponse> listarPerguntas(EntityManager entityManager, Long id) {
		String sql = "select new br.com.zupacademy.caico.mercadolivre.detalhaproduto.PerguntaResponse"
				 + "(titulo, usuario)"
				 + " from PerguntasProdutos "
				 + "where produto_id = :produto_id";
	
		TypedQuery<PerguntaResponse> query = entityManager.createQuery(sql, PerguntaResponse.class);
		query.setParameter("produto_id", id);
	
		List<PerguntaResponse> pergunta = query.getResultList();
			
		return pergunta;

	}

}
