package com.nebrija.mvc.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nebrija.mvc.modelo.Usuario;
import com.nebrija.mvc.servicios.IUsuarioService;

import jakarta.validation.Valid;

@Controller
public class UsuariosController {
	
	@Autowired
	private IUsuarioService servicio;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*@GetMapping("/error500")
	public String triggerServerError() {
	    // Provocar una excepción para simular un error interno del servidor (código 500)
	    throw new RuntimeException();
	}*/
		
	@GetMapping("/login")
	public String login(@RequestParam(name = "error", required = false) String error, Model model, Authentication authentication) {
		if (error != null) {
            model.addAttribute("loginError", "Username o contraseña inválidos.");
        }
		else if (authentication != null && authentication.isAuthenticated()) {
			return "error/cerrar-sesion";
		}
		return "login";
	}
	
	//CREAR USUARIO
	@GetMapping("/registrar")
	public String registrar(Model model, Authentication authentication) {
	    if (authentication != null && authentication.isAuthenticated()) {
	        return "error/cerrar-sesion";
	    }
		model.addAttribute("userForm", new Usuario());
		return "registrar";
	}
	
	@PostMapping("/registrarUsuario")
	public String registrarUsuario(@ModelAttribute("userForm") @Valid Usuario usuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "registrar";
		}
		else {
			String contrasenaCodificada = passwordEncoder.encode(usuario.getContrasena());
            usuario.setContrasena(contrasenaCodificada);
			usuario.setRoles("ROLE_USER");
	        servicio.add(usuario);
	        System.out.println(usuario.toString());
	        return "redirect:/login";
		}
	}
	
	//LISTAR USUARIOS
	@GetMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("listaUsuarios", servicio.findAll());
		return "admin";
	}
	
	//EDITAR USUARIO
	@GetMapping("/editarUsuario/{id}")
	public String editarUsuario(@PathVariable int id, Model model) {
		Usuario usuario = servicio.findById(id);
		if (usuario != null) {
			model.addAttribute("userForm", usuario);
			return "editar";
		} else {
			return "redirect:/registrar";
		}
	}
	
	@PostMapping("/editarUsuarioSubmit")
	public String editarUsuarioSubmit(@Valid @ModelAttribute("userForm") Usuario usuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "registrar";
		} else {
			String contrasenaCodificada = passwordEncoder.encode(usuario.getContrasena());
            usuario.setContrasena(contrasenaCodificada);
			servicio.edit(usuario);
			return "redirect:/admin";
		}
	}
	
	//ELIMINAR USUARIO
	@GetMapping("/eliminarUsuario/{id}")
	public String eliminarUsuario(@PathVariable int id, Model model) {
		servicio.delete(id);
		return "redirect:/admin";
	}
	
}