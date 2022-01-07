package com.estudo.designpattern.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountDayEnumTest {

    @Test
    public void should_throw_illegal_argument_exception_isbn_null() {
        try {
            DiscountDayEnum discountDayEnum = DiscountDayEnum.getDiscountDay(null);
            fail();
        } catch (Exception e) {
            assertTrue(e.getClass() == IllegalArgumentException.class);
        }
    }

}
