package com.estudo.designpattern.user;

import com.estudo.designpattern.exception.RecordExistsException;
import com.estudo.designpattern.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UpdateUserController {

    private final UserRepository repository;

    public UpdateUserController(UserRepository repository) {
        this.repository = repository;
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<UserResponse> update(@Valid @PathVariable Long id, @RequestBody UpdateUserRequest request) {

        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " doesn't exist in the database"));

        if (request.getEmail().isPresent() && repository.existsByEmail(request.getEmail().get())) {
            throw new RecordExistsException(HttpStatus.UNPROCESSABLE_ENTITY, "E-mail already exists in the database");
        }

        user.updateData(request);

        User updatedUser = repository.save(user);
        UserResponse result = new UserResponse(updatedUser);

        return ResponseEntity.ok().body(result);
    }
}
