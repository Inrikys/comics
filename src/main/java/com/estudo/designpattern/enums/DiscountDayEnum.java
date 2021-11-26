package com.estudo.designpattern.enums;


public enum DiscountDayEnum {

    MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

    private int code;

    private DiscountDayEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static DiscountDayEnum valueOf(int code) {
        for (DiscountDayEnum value : DiscountDayEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid DiscountDay code");
    }
}