package com.nebrija.mvc.controladores;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.nebrija.mvc.modelo.Usuario;
import com.nebrija.mvc.modelo.Video;
import com.nebrija.mvc.repositorios.IUsuarioRepository;
import com.nebrija.mvc.servicios.IVideoService;
import com.nebrija.mvc.upload.storage.StorageService;

@Controller
public class VideosController {
	
	@Autowired
	private IUsuarioRepository userRepository;
	
	@Autowired
	private IVideoService servicio;
	
	@Autowired
	private StorageService storageService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("listaVideos", servicio.findAll());
		return "index";
	}
	
	@GetMapping("/upload")
	public String upload(Model model) {
		model.addAttribute("videoForm", new Video());
		return "upload";
	}
	
	@PostMapping("/subirVideo")
	public String subirVideo(@ModelAttribute("videoForm") Video video, @RequestParam("file") MultipartFile file, Principal principal, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "upload";
		}
		else {
			if (!file.isEmpty()) {
				String avatar = storageService.store(file, video.getId());
				video.setVideo(MvcUriComponentsBuilder
						.fromMethodName(VideosController.class, "serveFile", avatar).build().toUriString());
			}
	        String username = principal.getName();
	        Usuario usuario = userRepository.findByUsername(username);
	        video.setUsuario(usuario);
			servicio.add(video);
	        System.out.println(video.toString());
	        return "redirect:/";
		}
	}
	
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().body(file);
	}
	
}
