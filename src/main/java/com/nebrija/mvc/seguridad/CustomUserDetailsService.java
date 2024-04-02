package com.nebrija.mvc.seguridad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nebrija.mvc.modelo.Usuario;
import com.nebrija.mvc.repositorios.IUsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
    private IUsuarioRepository userRepository;

    // Constructor que inyecta el repositorio de usuarios
    public CustomUserDetailsService(IUsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // Método para cargar un usuario por su username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userRepository.findByUsername(username);
        // Verificar si el usuario existe
        if (usuario != null) {
            String userName = usuario.getUsername();
            String password = usuario.getContrasena();
            String role = usuario.getRoles();

            // Crea y devuelve un objeto UserDetails con la información del usuario
            return new org.springframework.security.core.userdetails.User(userName,
                    password,
                    mapRolesToAuthorities(role));
        } else {
            throw new UsernameNotFoundException("Username o password Inválido.");
        }
    }

    // Método para mapear los roles a Authorities para que sean reconocidos por Spring Security
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
	
}
