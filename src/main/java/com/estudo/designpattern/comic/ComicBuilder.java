package com.estudo.designpattern.comic;

// Fluent Builder
public class ComicBuilder {

    private Comic comic;

    private ComicBuilder() {

    }

    public static ComicBuilder getInstance() {
        ComicBuilder builder = new ComicBuilder();
        builder.comic = new Comic();
        return builder;
    }

    public ComicBuilder id(final Long id) {
        this.comic.setId(id);
        return this;
    }

    public ComicBuilder name(final String name) {
        this.comic.setName(name);
        return this;
    }

    public ComicBuilder description(final String description) {
        this.comic.setDescription(description);
        return this;
    }

    public ComicBuilder isbn(final String isbn) {
        this.comic.setIsbn(isbn);
        return this;
    }

    public ComicBuilder price(final Double price) {
        this.comic.setPrice(price);
        return this;
    }

    public Comic build() {
        return this.comic;
    }

}
