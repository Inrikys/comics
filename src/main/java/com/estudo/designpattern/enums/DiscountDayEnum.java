package com.estudo.designpattern.enums;


import java.util.Arrays;

public enum DiscountDayEnum {

    MONDAY(1, "Monday", new int[]{0, 1}),
    TUESDAY(2, "Tuesday", new int[]{2, 3}),
    WEDNESDAY(3, "Wednesday", new int[]{4, 5}),
    THURSDAY(4, "Thursday", new int[]{6, 7}),
    FRIDAY(5, "Friday", new int[]{8, 9});

    private int dayCode;
    private String label;
    private int[] lastIsbnCharacter;

    private DiscountDayEnum(int dayCode, String label, int[] lastIsbnCharacter) {
        this.dayCode = dayCode;
        this.label = label;
        this.lastIsbnCharacter = lastIsbnCharacter;
    }

    public int getCode() {
        return dayCode;
    }

    public String getLabel() {
        return label;
    }

    public int[] getIsbns() {
        return lastIsbnCharacter;
    }

    public static DiscountDayEnum getDiscountDay(String isbn) {

        if (isbn == null) {
            throw new IllegalArgumentException("Invalid ISBN");
        }

        if (!isbn.trim().equals("")) {
            Character lastCharacter = isbn.trim().charAt(isbn.length() - 1);
            int discountCode = Character.getNumericValue(lastCharacter);

            for (DiscountDayEnum value : DiscountDayEnum.values()) {
                if (Arrays.stream(value.getIsbns()).anyMatch(x -> x == discountCode)) {
                    return value;
                }
            }
        }

        throw new IllegalArgumentException("Invalid ISBN");
    }

    public static DiscountDayEnum getCodeByLabel(String discountDayLabel) {

        if (discountDayLabel == null) {
            throw new IllegalArgumentException("Invalid DiscountDay Label");
        }

        for (DiscountDayEnum value : DiscountDayEnum.values()) {
            if (value.getLabel() == discountDayLabel) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid DiscountDay Label");
    }
}