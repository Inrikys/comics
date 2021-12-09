package com.estudo.designpattern.enums;


import java.util.Arrays;

public enum DiscountDayEnum {

    MONDAY(1, "Segunda-feira", new int[]{0, 1}),
    TUESDAY(2, "Terça-feira", new int[]{2, 3}),
    WEDNESDAY(3, "Quarta-feira", new int[]{4, 5}),
    THURSDAY(4, "Quinta-feira", new int[]{6, 7}),
    FRIDAY(5, "Sexta-feira", new int[]{8, 9});

    private int code;
    private String label;
    private int[] lastIsbnCaractere;

    private DiscountDayEnum(int code, String label, int[] lastIsbnCaractere) {
        this.code = code;
        this.label = label;
        this.lastIsbnCaractere = lastIsbnCaractere;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public int[] getIsbns() {
        return lastIsbnCaractere;
    }

    public static DiscountDayEnum valueOf(int code) {
        for (DiscountDayEnum value : DiscountDayEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid DiscountDay code");
    }

    public static DiscountDayEnum getDiscountDay(int isbnCode) {
        for (DiscountDayEnum value : DiscountDayEnum.values()) {
            if (Arrays.stream(value.getIsbns()).anyMatch(x -> x == isbnCode)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid DiscountDay ISBN Code");
    }
}