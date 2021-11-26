package com.estudo.designpattern.user;

import com.estudo.designpattern.comic.Comic;

import java.time.Instant;

// Fluent Builder
public class UserBuilder {

    private User user;

    private UserBuilder() {
    }

    public static UserBuilder getInstance() {
        UserBuilder builder = new UserBuilder();
        builder.user = new User();
        return builder;
    }

    public UserBuilder id(final Long id) {
        this.user.setId(id);
        return this;
    }

    public UserBuilder name(final String name) {
        this.user.setName(name);
        return this;
    }

    public UserBuilder email(final String email) {
        this.user.setEmail(email);
        return this;
    }

    public UserBuilder cpf(final String cpf) {
        this.user.setCpf(cpf);
        return this;
    }

    public UserBuilder dob(final Instant dob) {
        this.user.setDob(dob);
        return this;
    }

    public UserBuilder comic(final Comic comic) {
        this.user.getComics().add(comic);
        return this;
    }

    public User build() {
        return this.user;
    }
}