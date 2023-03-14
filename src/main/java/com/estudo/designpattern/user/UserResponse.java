package com.estudo.designpattern.user;

import com.estudo.designpattern.comic.ComicResponse;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class UserResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String cpf;
    private LocalDate dob;

    private Set<ComicResponse> comics;

    public UserResponse(Long id, String name, String email, String cpf, LocalDate dob, Set<ComicResponse> comics) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.dob = dob;
        this.comics = comics;
    }

    public UserResponse(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.cpf = entity.getCpf();
        this.dob = entity.getDob();

        if (entity.getComics() != null) {
            this.comics = entity.getComics().stream().map(ComicResponse::new).collect(Collectors.toSet());
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Set<ComicResponse> getComics() {
        return comics;
    }
}
