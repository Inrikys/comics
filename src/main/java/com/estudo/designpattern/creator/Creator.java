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
        Creator other = (Creator) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
