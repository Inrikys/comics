package com.estudo.designpattern.creator;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.estudo.designpattern.comic.Comic;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_creator")
public class Creator implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String role;

    @JsonIgnore
    @ManyToMany(mappedBy = "creators")
    private Set<Comic> comics = new HashSet<>();

    @Deprecated
    /**
     * @deprecated Framework eyes only
     */
    public Creator() {
    }

    public Creator(Long id, String name, String role) {
        super();
        this.id = id;
        this.name = name;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Comic> getComics() {
        return comics;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder() {

        private Long id;

        private String name;

        private String role;

        private Set<Comic> comics = new HashSet<>();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder comics(Set<Comic> comics) {
            this.comics = comics;
            return this;
        }

        public Creator build() {
            return new Creator(id, name, role);
        }
    }
}
