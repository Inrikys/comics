package com.estudo.designpattern.user;

import com.estudo.designpattern.comic.Comic;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String cpf;

    private LocalDate dob;

    private String image;

    @ManyToMany
    @JoinTable(name = "tb_user_comic", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "comic_id"))
    private Set<Comic> comics = new HashSet<>();

    @Deprecated
    /**
     * @deprecated framework eyes only
     */
    public User() {
    }

    public User(Long id, String name, String email, String cpf, LocalDate dob, String image) {
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

    public Set<Comic> getComics() {
        return comics;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private String name;
        private String email;
        private String cpf;
        private LocalDate dob;
        private String image;
        private Set<Comic> comics = new HashSet<>();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder dob(LocalDate dob) {
            this.dob = dob;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder comics(Set<Comic> comics) {
            this.comics = comics;
            return this;
        }

        public User build() {
            return new User(id, name, email, cpf, dob, image);
        }
    }
}