package com.estudo.designpattern.feign.marvel;

import com.estudo.designpattern.feign.marvel.dto.MarvelComicResponse;

public interface MarvelFeign {

    MarvelComicResponse getComicById(Long id, String ts, String apikey, String hash);

}
