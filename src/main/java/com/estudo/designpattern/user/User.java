package com.estudo.designpattern.user;

import com.estudo.designpattern.annotations.Cpf;
import com.estudo.designpattern.comic.Comic;
import com.estudo.designpattern.exception.MethodArgumentException;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome obrigatório")
    private String name;

    @NotBlank(message = "E-mail obrigatório")
    @Email(message = "E-mail inválido")
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    @Size(min = 14, max = 14)
    @Cpf(message = "CPF inválido")
    @NotNull(message = "CPF obrigatório")
    private String cpf;

    @NotNull(message = "Data de nascimento obrigatória")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Date dob;

    private String image;

    @ManyToMany
    @JoinTable(name = "tb_user_comic", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "comic_id"))
    private Set<Comic> comics = new HashSet<>();

    @Deprecated
    public User() {

    }

    public User(Long id, String name, String email, String cpf, Date dob, String image) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.dob = dob;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void parseDob(String dob) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.dob = sdf.parse(dob);
        } catch (ParseException e) {
            throw new MethodArgumentException("Invalid string to parse into DOB");
        }
    }

    public Set<Comic> getComics() {
        return comics;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}