package com.nebrija.mvc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nebrija.mvc.modelo.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByUsername(String username);
	
}