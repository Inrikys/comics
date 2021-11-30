package com.estudo.designpattern.comic;

import com.estudo.designpattern.creator.Creator;
import com.estudo.designpattern.creator.CreatorRepository;
import com.estudo.designpattern.exception.DatabaseException;
import com.estudo.designpattern.exception.ResourceNotFoundException;
import com.estudo.designpattern.feign.marvel.MarvelClient;
import com.estudo.designpattern.feign.marvel.dto.Item;
import com.estudo.designpattern.feign.marvel.dto.MarvelComicResponse;
import com.estudo.designpattern.feign.marvel.dto.Price;
import com.estudo.designpattern.feign.marvel.dto.Result;
import com.estudo.designpattern.user.User;
import com.estudo.designpattern.user.UserRepository;
import com.estudo.designpattern.user.UserResponse;
import feign.FeignException;
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
	private MarvelClient marvelApi;

	private final String APIKEY = "85c27bd1a7f22584b5eacd853bf278fd";
	private final String TS = "zupper";
	private final String HASH = "a444cce1b354739736b7c46516ed63aa";

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

	public MarvelComicResponse findByIdFromMarvelApi(Long id) {
		try {
			return marvelApi.getComicById(id, TS, APIKEY, HASH);
		} catch (FeignException e) {
			throw e;
		}
	}

	public UserResponse registerComicFromMarvelApi(Long comicId, Long userId) {
		try {
			MarvelComicResponse marvelComicResponse = marvelApi.getComicById(comicId, TS, APIKEY, HASH);

			// Resgata dados da Marvel API
			Comic comicObj = new Comic();
			for (Result x : marvelComicResponse.getData().getResults()) {
				comicObj.setId(Long.valueOf(x.getId()));
				comicObj.setName(x.getTitle());
				comicObj.setDescription(x.getDescription());
				comicObj.setIsbn(x.getIsbn());

				for (Price p : x.getPrices()) {
					comicObj.setPrice(p.getPrice());
				}

				// Dados dos autores da Comic
				for (Item i : x.getCreators().getItems()) {
					Creator creator = new Creator();

					// Pegar ID do autor pela URL
					int index = i.getResourceURI().lastIndexOf("/");
					Long creatorId = Long.parseLong(i.getResourceURI().substring(index + 1));

					// Salva informações necessárias
					creator.setId(creatorId);
					creator.setName(i.getName());
					creator.setRole(i.getRole());

					comicObj.getCreators().add(creator);
				}

			}

			creatorRepository.saveAll(comicObj.getCreators());
			comicRepository.save(comicObj);

			// Vincula Comic com o usuário
			Optional<User> userOptional = userRepository.findById(userId);
			User userObj = userOptional.get();
			userObj.getComics().add(comicObj);

			User userResult = userRepository.save(userObj);
			UserResponse result = new UserResponse(userResult);
			return result;

		} catch (FeignException e) {
			throw e;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(userId);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

}
