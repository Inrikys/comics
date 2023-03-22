package com.estudo.designpattern.user;

import java.util.Optional;

public class UpdateUserRequest implements UpdateUserData {

    public String name;
    public String email;
    public String image;

    @Override
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    @Override
    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    @Override
    public Optional<String> getImage() {
        return Optional.ofNullable(image);
    }
}
