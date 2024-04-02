package com.nebrija.mvc.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.nebrija.mvc.modelo.Usuario;
import com.nebrija.mvc.repositorios.IUsuarioRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Primary
@Service("usuarioServiceImpl")
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private IUsuarioRepository repositorio;
	
	@Override
	public Usuario add(Usuario usuario) {
		return repositorio.save(usuario);
	}

	@Override
	public List<Usuario> findAll() {
		return repositorio.findAll();
	}

	@Override
	public Usuario findById(int id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public Usuario edit(Usuario usuario) {
		return repositorio.save(usuario);
	}
	
	@Override
	public void delete(int id) {
		repositorio.deleteById(id);
	}
	
}
