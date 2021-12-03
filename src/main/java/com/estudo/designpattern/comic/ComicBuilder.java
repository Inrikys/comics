package com.estudo.designpattern.comic;

import com.estudo.designpattern.creator.CreatorBuilder;
import com.estudo.designpattern.user.UserBuilder;

import java.text.ParseException;

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

    public static ComicBuilder getMockedInstance() throws ParseException {
        ComicBuilder builder = new ComicBuilder();
        builder.comic = new Comic();
        builder.comic.setId(40L);
        builder.comic.setName("Batman by Grant Morrison Omnibus Vol. 3");
        builder.comic.setPrice(30.00);
        builder.comic.setIsbn("1401276452");
        builder.comic.setDescription("Morrison continues his earth-shattering run on the Batman titles with this exciting series illustrated by hot artist Yanick Paquette who features the next stage of evolution of the Dark Knight. Bruce Wayne publicly announces that he is the financial backer of Batman and establishes a worldwide franchise of Batman that will protect the entire globe.");

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
