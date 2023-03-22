package com.estudo.designpattern.user;

import java.util.Optional;

public interface UpdateUserData {

    Optional<String> getName();
    Optional<String> getEmail();
    Optional<String> getImage();

}
