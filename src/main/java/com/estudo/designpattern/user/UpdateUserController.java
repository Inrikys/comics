package com.estudo.designpattern.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UpdateUserController {

    private final UserService service;

    public UpdateUserController(UserService service) {
        this.service = service;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> update(@Valid @PathVariable Long id, @RequestBody User obj) {

//        User user = repository.findById(id)
//                .orElseThrow(() -> new UserExistsException(UNPROCESSABLE_ENTITY, "User doesn't exist in database"));

        UserResponse result = service.update(id, obj);
        return ResponseEntity.ok().body(result);
    }
}
