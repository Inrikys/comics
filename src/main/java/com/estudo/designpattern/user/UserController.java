package com.estudo.designpattern.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        UserResponse obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<UserResponse> insert(@Valid @RequestBody User obj) {
        UserResponse result = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> update(@Valid @PathVariable Long id, @RequestBody User obj) {
        UserResponse result = service.update(id, obj);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Working with form-data and files
    @PostMapping(value = "/form-data")
    public void createNewObjectWithFile(@RequestParam("user") String user, @RequestParam(value = "file", required = false) MultipartFile file,
                                         @RequestParam(value = "file1", required = false) MultipartFile file1){
        System.out.println(user);
        System.out.println(file);
        System.out.println(file1);
    }

}
