package com.nebrija.mvc.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "{username.no.vacio}")
	private String username;
	
	@NotEmpty(message = "{username.no.vacio}")
	@Email(message = "{email.formato.invalido}")
	private String email;
	
	@NotEmpty(message = "{username.no.vacio}")
	private String contrasena;
	
	private String roles;

	public Usuario() {
		
	}
	
	public Usuario(String username, String email, String contrasena, String roles) {
		this.username = username;
		this.email = email;
		this.contrasena = contrasena;
		this.roles = roles;
	}

	public Usuario(int id, String username, String email, String contrasena, String roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.contrasena = contrasena;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", email=" + email + ", contrasena=" + contrasena
				+ ", roles=" + roles + "]";
	}

}
