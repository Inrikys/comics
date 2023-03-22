package com.estudo.designpattern.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class CreateUserRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "E-mail is required")
    @Email(message = "E-mail inválido")
    private String email;

    @CPF(message = "CPF is required")
    @NotNull(message = "CPF is required")
    private String cpf;

    @NotNull(message = "Date of birth is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private String image;

    @Deprecated
    /**
     * @deprecated framework eyes only
     */
    public CreateUserRequest() {
    }

    public CreateUserRequest(String name, String email, String cpf, LocalDate dob, String image) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.dob = dob;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User toModel() {
        return User.builder()
                .name(name)
                .email(email)
                .cpf(cpf)
                .dob(dob)
                .image(image)
                .build();
    }
}