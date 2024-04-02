package com.nebrija.mvc.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Video {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String video;
	private String titulo;
	
	@ManyToOne
	private Usuario usuario;
	
	public Video() {
		
	}
	
	/////////////////////////////////////////////
	public Video(String titulo, Usuario usuario) {
		this.titulo = titulo;
		this.usuario = usuario;
	}
	/////////////////////////////////////////////
	
	public Video(String video, String titulo, Usuario usuario) {
		this.video = video;
		this.titulo = titulo;
		this.usuario = usuario;
	}
	
	public Video(int id, String video, String titulo, Usuario usuario) {
		this.id = id;
		this.video = video;
		this.titulo = titulo;
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", video=" + video + ", titulo=" + titulo + ", usuario=" + usuario + "]";
	}
	
	
}
