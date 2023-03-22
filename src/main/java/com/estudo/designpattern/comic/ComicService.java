package com.estudo.designpattern.comic;

import com.estudo.designpattern.creator.Creator;
import com.estudo.designpattern.creator.CreatorRepository;
import com.estudo.designpattern.exception.DatabaseException;
import com.estudo.designpattern.exception.ResourceNotFoundException;
import com.estudo.designpattern.client.marvel.dto.Item;
import com.estudo.designpattern.client.marvel.dto.MarvelComicResponse;
import com.estudo.designpattern.client.marvel.dto.Price;
import com.estudo.designpattern.client.marvel.dto.Result;
import com.estudo.designpattern.marvel.comic.MarvelComicService;
import com.estudo.designpattern.user.User;
import com.estudo.designpattern.user.UserRepository;
import com.estudo.designpattern.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComicService {

    @Autowired
    private ComicRepository comicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreatorRepository creatorRepository;

    @Autowired
    private MarvelComicService marvelComicService;

    public ComicService() {
    }

    public List<ComicResponse> findAll() {
        List<Comic> result = comicRepository.findAll();
        return result.stream().map(x -> new ComicResponse(x)).collect(Collectors.toList());
    }

    public ComicResponse findById(Long id) {
        try {
            Optional<Comic> obj = comicRepository.findById(id);
            ComicResponse result = new ComicResponse(obj.get());
            return result;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }


    public UserResponse registerComicFromMarvelApi(Long comicId, Long userId) {
        try {
            // Resgata dados da Marvel API
            MarvelComicResponse marvelComicResponse = marvelComicService.findById(comicId);

            Comic comicObj = this.formatComic(marvelComicResponse);

            creatorRepository.saveAll(comicObj.getCreators());
            comicRepository.save(comicObj);

            // Vincula Comic com o usuário
            Optional<User> userOptional = userRepository.findById(userId);
            User userObj = userOptional.get();
            userObj.getComics().add(comicObj);

            User userResult = userRepository.save(userObj);
            UserResponse result = new UserResponse(userResult);
            return result;

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(userId);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private Comic formatComic(MarvelComicResponse marvelComicResponse) {
        Comic comicObj = ComicBuilder.getInstance().build();
        for (Result comic : marvelComicResponse.getData().getResults()) {
            comicObj.setId(Long.valueOf(comic.getId()));
            comicObj.setName(comic.getTitle());
            comicObj.setDescription(comic.getDescription());
            comicObj.setIsbn(comic.getIsbn());
            comicObj.setThumbnail(comic.getThumbnail().getPath() + "." + comic.getThumbnail().getExtension());

            for (Price p : comic.getPrices()) {
                comicObj.setPrice(p.getPrice());
            }

            // Dados dos autores da Comic
            for (Item i : comic.getCreators().getItems()) {

                // ID do autor pela URL
                int index = i.getResourceURI().lastIndexOf("/");
                Long creatorId = Long.parseLong(i.getResourceURI().substring(index + 1));

                // Salva informações necessárias
                Creator creator = Creator.builder()
                        .id(creatorId)
                        .name(i.getName())
                        .role(i.getRole())
                        .build();

                comicObj.getCreators().add(creator);
            }
        }

        return comicObj;
    }

}
