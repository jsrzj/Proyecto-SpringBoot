package com.nebrija.mvc.servicios;

import java.util.List;

import com.nebrija.mvc.modelo.Video;

public interface IVideoService {
	
	public Video add(Video video);
	
	public List <Video> findAll();
	
	public Video findById(int id);
	
	public Video edit (Video video);
	
	public void delete(int id);
}
