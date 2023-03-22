package com.estudo.designpattern.creator;

import com.estudo.designpattern.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/creators")
public class FindCreatorController {

    private final CreatorRepository repository;

    public FindCreatorController(CreatorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<CreatorResponse>> findAll() {

        List<Creator> creators = repository.findAll();

        List<CreatorResponse> list = creators.stream().map(CreatorResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CreatorResponse> findById(@PathVariable Long id) {

        Creator creator = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Creator with id " + id + " not found in the database"));

        CreatorResponse obj = new CreatorResponse(creator);
        return ResponseEntity.ok().body(obj);
    }

}
