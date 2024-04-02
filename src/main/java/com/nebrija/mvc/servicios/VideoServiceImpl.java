package com.nebrija.mvc.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebrija.mvc.modelo.Video;
import com.nebrija.mvc.repositorios.IVideoRepository;

@Service
public class VideoServiceImpl implements IVideoService {

	@Autowired
	private IVideoRepository repositorio;
	
	@Override
	public Video add(Video video) {
		return repositorio.save(video);
	}

	@Override
	public List<Video> findAll() {
		return repositorio.findAll();
	}

	@Override
	public Video findById(int id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public Video edit(Video video) {
		return repositorio.save(video);
	}

	@Override
	public void delete(int id) {
		repositorio.deleteById(id);
	}

}
