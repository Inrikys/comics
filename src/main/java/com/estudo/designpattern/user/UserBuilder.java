package com.estudo.designpattern.user;

import com.estudo.designpattern.comic.Comic;

import java.util.Date;

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

    public static UserBuilder getMockedInstance() {
        UserBuilder builder = new UserBuilder();
        builder.user = new User();
        builder.user.setId(1L);
        builder.user.setName("Testerson da Silva");
        builder.user.setEmail("henriquezuper@zup.com.br");
        builder.user.setCpf("417.701.438-90");
        builder.user.parseDob("1999-02-20");

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

    public UserBuilder dob(final Date dob) {
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