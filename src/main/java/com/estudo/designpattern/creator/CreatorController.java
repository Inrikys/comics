package com.estudo.designpattern.creator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/creators")
public class CreatorController {
	
	@Autowired
	private CreatorService service;
	
	@GetMapping
	public ResponseEntity<List<CreatorResponse>> findAll() {
		List<CreatorResponse> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<CreatorResponse> findById(@PathVariable Long id){
		CreatorResponse obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
