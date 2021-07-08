package br.com.zupacademy.caico.mercadolivre.detalhaproduto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;

public class OpiniaoResponse {
	
	private Integer nota;
	private String titulo;
	private String descricao;
	private String usuario;
	
	@Deprecated
	public OpiniaoResponse() {
	}
	
	public OpiniaoResponse(Integer nota, String titulo, String descricao, Usuarios usuario) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario.getLogin();
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public Integer getNota() {
		return nota;
	}
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<OpiniaoResponse> listarOpinioes(EntityManager entityManager, Long id) {
		String sql = "select new br.com.zupacademy.caico.mercadolivre.detalhaproduto.OpiniaoResponse"
				 + "(nota, titulo, descricao, usuario)"
				 + " from OpiniaoProdutos "
				 + "where produto_id = :produto_id";
	
		TypedQuery<OpiniaoResponse> query = entityManager.createQuery(sql, OpiniaoResponse.class);
		query.setParameter("produto_id", id);
	
		List<OpiniaoResponse> opiniao = query.getResultList();
			
		return opiniao;
	}
	
}
