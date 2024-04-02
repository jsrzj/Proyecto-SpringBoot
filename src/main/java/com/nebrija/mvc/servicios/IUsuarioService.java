package com.nebrija.mvc.servicios;

import java.util.List;

import com.nebrija.mvc.modelo.Usuario;

public interface IUsuarioService {
	
	public Usuario add(Usuario usuario);
	
	public List<Usuario> findAll();
	
	public Usuario findById(int id);
	
	public Usuario edit(Usuario usuario);
	
	public void delete(int id);

		
}
