package com.estudo.designpattern.creator;

// Fluent Builder
public class CreatorBuilder {

    private Creator creator;

    private CreatorBuilder() {
    }

    public static CreatorBuilder getInstance() {
        CreatorBuilder builder = new CreatorBuilder();
        builder.creator = new Creator();
        return builder;
    }

    public CreatorBuilder id(final Long id) {
        this.creator.setId(id);
        return this;
    }

    public CreatorBuilder name(final String name) {
        this.creator.setName(name);
        return this;
    }

    public CreatorBuilder role(final String role) {
        this.creator.setRole(role);
        return this;
    }

    public Creator build() {
        return this.creator;
    }
}
