package br.com.zupacademy.caico.mercadolivre.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zupacademy.caico.mercadolivre.cadastrousuario.UsuarioRepository;
import br.com.zupacademy.caico.mercadolivre.cadastrousuario.Usuarios;


@Service
public class AutenticacaoService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuarios usuario = usuarioRepository.findByLogin(username);   
		System.out.println("Teste Aqui LoadUserByUserName: " + username);
		
		if (usuario != null) {
			return usuario;
		} 			
		
		throw new UsernameNotFoundException("Dados inválidos");
	}

	
}
