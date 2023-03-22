package com.estudo.designpattern.marvel.comic;

import com.estudo.designpattern.client.marvel.dto.MarvelComicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/marvel/comics")
public class MarvelComicController {

    @Autowired
    MarvelComicService marvelComicService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MarvelComicResponse> findById(@PathVariable Long id) {
        MarvelComicResponse obj = marvelComicService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
