package br.com.zupacademy.caico.mercadolivre.cadastrousuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long>{

	Usuarios findByLogin(String username);

}
