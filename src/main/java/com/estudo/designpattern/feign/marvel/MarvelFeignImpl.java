package com.estudo.designpattern.feign.marvel;

import com.estudo.designpattern.feign.marvel.dto.MarvelComicResponse;
import org.springframework.stereotype.Component;

@Component
public class MarvelFeignImpl implements MarvelFeign {

    private final MarvelClient marvelClient;

    public MarvelFeignImpl(MarvelClient marvelClient) {
        this.marvelClient = marvelClient;
    }

    @Override
    public MarvelComicResponse getComicById(Long id, String ts, String apikey, String hash) {
        try {
            this.marvelClient.getComicById(id, ts, apikey, hash);
        } catch (Exception e) {

        }
        return null;
    }

}
