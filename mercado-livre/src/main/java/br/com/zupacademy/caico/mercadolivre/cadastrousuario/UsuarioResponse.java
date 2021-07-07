package br.com.zupacademy.caico.mercadolivre.cadastrousuario;

public class UsuarioResponse {
	
	private Long id;
	private String username;
	public UsuarioResponse(Long id, String username) {
		this.id = id;
		this.username = username;
	}
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}

}
