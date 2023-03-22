package com.estudo.designpattern.user;

import com.estudo.designpattern.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class FindUserController {

    private final UserRepository repository;

    public FindUserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {

        List<User> users = repository.findAll();

        List<UserResponse> list = users.stream().map(UserResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {

        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        UserResponse obj = new UserResponse(user);
        return ResponseEntity.ok().body(obj);
    }

}
