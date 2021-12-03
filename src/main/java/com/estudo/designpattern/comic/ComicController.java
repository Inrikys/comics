package com.estudo.designpattern.comic;

import com.estudo.designpattern.feign.marvel.dto.MarvelComicResponse;
import com.estudo.designpattern.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/comics")
public class ComicController {

	@Autowired
	private ComicService service;

	@GetMapping
	public ResponseEntity<List<ComicResponse>> findAll() {
		List<ComicResponse> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ComicResponse> findById(@PathVariable Long id) {
		ComicResponse obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/register/{comicId}/{userId}")
	public ResponseEntity<UserResponse> registerComicFromMarvelApi(@PathVariable Long comicId, @PathVariable Long userId) {
		UserResponse obj = service.registerComicFromMarvelApi(comicId, userId);
		return ResponseEntity.ok().body(obj);
	}

}
