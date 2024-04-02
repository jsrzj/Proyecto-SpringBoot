package com.nebrija.mvc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nebrija.mvc.modelo.Video;

public interface IVideoRepository extends JpaRepository<Video, Integer> {
	
}
