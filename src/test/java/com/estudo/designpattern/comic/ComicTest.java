package com.estudo.designpattern.comic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComicTest {

    @BeforeEach
    public void setup() {

    }

    @Test
    public void should_return_monday_as_discount_day() {
        Comic comic1 = ComicBuilder.getMockedInstance().build();
        Comic comic2 = ComicBuilder.getMockedInstance().build();
        comic1.setIsbn("1401276450");
        comic2.setIsbn("1401276451");

        assertAll(
                () -> assertTrue(comic1.getDiscountDay() == "Monday"),
                () -> assertTrue(comic2.getDiscountDay() == "Monday")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"1401276452", "1401276453"})
    public void should_return_tuesday_as_discount_day(String isbn) {
        Comic comic = ComicBuilder.getMockedInstance().build();
        comic.setIsbn(isbn);

        assertTrue(comic.getDiscountDay() == "Tuesday");
    }

    // CSV stands for Comma Separated Value
    @ParameterizedTest(name = "isbn1={0}, isbn2={1}")
    @CsvSource(value = {"1401276454, 1401276455"})
    public void should_return_wednesday_as_discount_day(String isbn1, String isbn2) {
        Comic comic1 = ComicBuilder.getMockedInstance().build();
        Comic comic2 = ComicBuilder.getMockedInstance().build();
        comic1.setIsbn(isbn1);
        comic2.setIsbn(isbn2);

        assertAll(
                () -> assertTrue(comic1.getDiscountDay() == "Wednesday"),
                () -> assertTrue(comic2.getDiscountDay() == "Wednesday")
        );
    }

    @Test
    public void should_return_thursday_as_discount_day() {
        Comic comic1 = ComicBuilder.getMockedInstance().build();
        Comic comic2 = ComicBuilder.getMockedInstance().build();
        comic1.setIsbn("1401276456");
        comic2.setIsbn("1401276457");

        assertAll(
                () -> assertTrue(comic1.getDiscountDay() == "Thursday"),
                () -> assertTrue(comic2.getDiscountDay() == "Thursday")
        );
    }

    @Test
    public void should_return_friday_as_discount_day() {
        Comic comic1 = ComicBuilder.getMockedInstance().build();
        Comic comic2 = ComicBuilder.getMockedInstance().build();
        comic1.setIsbn("1401276458");
        comic2.setIsbn("1401276459");
        assertAll(
                () -> assertTrue(comic1.getDiscountDay() == "Friday"),
                () -> assertTrue(comic2.getDiscountDay() == "Friday")
        );

    }

}
