package com.estudo.designpattern.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class UserRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Nome obrigatório")
    private String name;

    @NotBlank(message = "E-mail obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @CPF(message = "CPF inválido")
    @NotNull(message = "CPF obrigatório")
    private String cpf;

    @NotNull(message = "Data de nascimento obrigatória")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private String image;

    @Deprecated
    /**
     * @deprecated framework eyes only
     */
    public UserRequest() {
    }

    public UserRequest(String name, String email, String cpf, LocalDate dob, String image) {
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