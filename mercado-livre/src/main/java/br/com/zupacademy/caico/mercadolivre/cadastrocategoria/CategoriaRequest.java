package br.com.zupacademy.caico.mercadolivre.cadastrocategoria;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import br.com.zupacademy.caico.mercadolivre.validators.NaoCadastrado;
import br.com.zupacademy.caico.mercadolivre.validators.UniqueValue;

public class CategoriaRequest {

	@NotBlank @UniqueValue(domainClass = Categorias.class, fieldName = "nome", message = "Já existe uma categoria cadastrada com esse nome!")
	private String nome;
	@NaoCadastrado(domainClass = Categorias.class, fieldName = "id", message = "Categoria mãe não cadastrada!")
	private Long categoria_mae_id;

	@Deprecated
	public CategoriaRequest() {
		
	}
	
	public CategoriaRequest(@NotBlank String nome) {
		this.nome = nome;
	}

	public Long getCategoria_mae_id() {
		return categoria_mae_id;
	}

	public void setCategoria_mae_id(Long categoria_mae_id) {
		this.categoria_mae_id = categoria_mae_id;
	}

	public String getNome() {
		return nome;
	}

	public Categorias toModel(@Valid CategoriaRequest categoriaRequest, EntityManager entityManager) {
		Categorias categoria = new Categorias(categoriaRequest.getNome());
		
		if (categoria_mae_id != null) {
			System.out.println("Caico: " + categoriaRequest.getCategoria_mae_id());
			Categorias categoriaMae = entityManager.find(Categorias.class, categoriaRequest.getCategoria_mae_id());
			Assert.state(categoriaMae != null, "Categoria mãe não cadastrada");
			categoria.setCategoria(categoriaMae);
		} 
		
		entityManager.persist(categoria);
		
		return categoria;
	}
	
}
