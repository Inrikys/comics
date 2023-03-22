package com.estudo.designpattern.user;

import com.estudo.designpattern.exception.RecordExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class CreateUserController {

    private final UserRepository repository;

    public CreateUserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<UserResponse> insert(@Valid @RequestBody CreateUserRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new RecordExistsException(HttpStatus.UNPROCESSABLE_ENTITY, "E-mail already exists in the database");
        }

        if (repository.existsByCpf(request.getCpf())) {
            throw new RecordExistsException(HttpStatus.UNPROCESSABLE_ENTITY, "CPF already exists in the database");
        }

        User model = request.toModel();

        User savedUser = repository.save(model);
        UserResponse response = new UserResponse(savedUser);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
