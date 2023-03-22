package com.estudo.designpattern.user;

import com.estudo.designpattern.exception.RecordExistsException;
import com.estudo.designpattern.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/users")
public class DeleteUserController {

    private final UserRepository repository;

    public DeleteUserController(UserRepository repository) {
        this.repository = repository;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
