package com.estudo.designpattern.comic;

import com.estudo.designpattern.util.DateUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ComicTest {


    @Nested
    class DiscountDayTests {

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

        @Test
        public void should_return_tuesday_as_discount_day() {
            Comic comic1 = ComicBuilder.getMockedInstance().build();
            Comic comic2 = ComicBuilder.getMockedInstance().build();
            comic1.setIsbn("1401276452");
            comic2.setIsbn("1401276453");

            assertAll(
                    () -> assertTrue(comic1.getDiscountDay() == "Tuesday"),
                    () -> assertTrue(comic2.getDiscountDay() == "Tuesday")
            );
        }

        @Test
        public void should_return_wednesday_as_discount_day() {
            Comic comic1 = ComicBuilder.getMockedInstance().build();
            Comic comic2 = ComicBuilder.getMockedInstance().build();
            comic1.setIsbn("1401276454");
            comic2.setIsbn("1401276455");
            assertAll(
                    () -> assertTrue(comic1.getDiscountDay() == "Wednesday"),
                    () -> assertTrue(comic2.getDiscountDay() == "Wednesday")
            );
        }

        // Parameterized
        @ParameterizedTest
        @ValueSource(strings = {"1401276456", "1401276457"})
        public void should_return_thursday_as_discount_day(String isbn) {
            Comic comic = ComicBuilder.getMockedInstance().build();
            comic.setIsbn(isbn);

            assertTrue(comic.getDiscountDay() == "Thursday");
        }

        // CSV stands for Comma Separated Value
        @ParameterizedTest(name = "isbn1={0}, isbn2={1}")
        @CsvSource(value = {"1401276458, 1401276459"})
        public void should_return_friday_as_discount_day(String isbn1, String isbn2) {
            Comic comic1 = ComicBuilder.getMockedInstance().build();
            Comic comic2 = ComicBuilder.getMockedInstance().build();
            comic1.setIsbn(isbn1);
            comic2.setIsbn(isbn2);

            assertAll(
                    () -> assertTrue(comic1.getDiscountDay() == "Friday"),
                    () -> assertTrue(comic2.getDiscountDay() == "Friday")
            );
        }
    }

    @Nested
    class DiscountActiveTests {
        // Assumptions
        // Set a rule to run or not the test case
        @Test
        public void should_return_discount_active_true_on_monday() {
            Assumptions.assumeTrue(DateUtil.getCurrentDayOfWeek() == 1);
            Comic comic1 = ComicBuilder.getMockedInstance().build();
            Comic comic2 = ComicBuilder.getMockedInstance().build();
            comic1.setIsbn("1401276450");
            comic2.setIsbn("1401276451");

            assertAll(
                    () -> assertTrue(comic1.getDiscountActive()),
                    () -> assertTrue(comic2.getDiscountActive())
            );
        }

        @Test
        public void should_return_discount_active_true_on_tuesday() {
            Assumptions.assumeTrue(DateUtil.getCurrentDayOfWeek() == 2);
            Comic comic1 = ComicBuilder.getMockedInstance().build();
            Comic comic2 = ComicBuilder.getMockedInstance().build();
            comic1.setIsbn("1401276452");
            comic2.setIsbn("1401276453");

            assertAll(
                    () -> assertTrue(comic1.getDiscountActive()),
                    () -> assertTrue(comic2.getDiscountActive())
            );
        }

        @Test
        public void should_return_discount_active_true_on_wednesday() {
            Assumptions.assumeTrue(DateUtil.getCurrentDayOfWeek() == 3);
            Comic comic1 = ComicBuilder.getMockedInstance().build();
            Comic comic2 = ComicBuilder.getMockedInstance().build();
            comic1.setIsbn("1401276454");
            comic2.setIsbn("1401276455");

            assertAll(
                    () -> assertTrue(comic1.getDiscountActive()),
                    () -> assertTrue(comic2.getDiscountActive())
            );
        }

        @Test
        public void should_return_discount_active_true_on_thursday() {
            Assumptions.assumeTrue(DateUtil.getCurrentDayOfWeek() == 4);
            Comic comic1 = ComicBuilder.getMockedInstance().build();
            Comic comic2 = ComicBuilder.getMockedInstance().build();
            comic1.setIsbn("1401276456");
            comic2.setIsbn("1401276457");

            assertAll(
                    () -> assertTrue(comic1.getDiscountActive()),
                    () -> assertTrue(comic2.getDiscountActive())
            );
        }

        @Test
        public void should_return_discount_active_true_on_friday() {
            Assumptions.assumeTrue(DateUtil.getCurrentDayOfWeek() == 5);
            Comic comic1 = ComicBuilder.getMockedInstance().build();
            Comic comic2 = ComicBuilder.getMockedInstance().build();
            comic1.setIsbn("1401276458");
            comic2.setIsbn("1401276459");

            assertAll(
                    () -> assertTrue(comic1.getDiscountActive()),
                    () -> assertTrue(comic2.getDiscountActive())
            );
        }
    }


    @Nested
    class PropertiesTypesTests {

        @Test
        public void verify_if_id_is_long() {
            Comic comic = ComicBuilder.getMockedInstance().build();
            assertThat(comic.getId()).isInstanceOf(Long.class);
        }

        @Test
        public void verify_if_name_is_string() {
            Comic comic = ComicBuilder.getMockedInstance().build();
            assertThat(comic.getName()).isInstanceOf(String.class);
        }

        @Test
        public void verify_if_price_is_double() {
            Comic comic = ComicBuilder.getMockedInstance().build();
            assertThat(comic.getPrice()).isInstanceOf(Double.class);
        }

        @Test
        public void verify_if_isbn_is_string() {
            Comic comic = ComicBuilder.getMockedInstance().build();
            assertThat(comic.getIsbn()).isInstanceOf(String.class);
        }

        @Test
        public void verify_if_description_is_string() {
            Comic comic = ComicBuilder.getMockedInstance().build();
            assertThat(comic.getDescription()).isInstanceOf(String.class);
        }
    }


}
