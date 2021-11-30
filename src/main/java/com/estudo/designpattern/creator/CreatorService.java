package com.estudo.designpattern.creator;

import com.estudo.designpattern.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreatorService {

	@Autowired
	private CreatorRepository repository;

	public List<CreatorResponse> findAll() {
		List<Creator> result = repository.findAll();
		return result.stream().map(x -> new CreatorResponse(x)).collect(Collectors.toList());
	}
	
	public CreatorResponse findById(Long id) {
		try {
			Optional<Creator> obj = repository.findById(id);
			CreatorResponse result = new CreatorResponse(obj.get());
			return result;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

}

