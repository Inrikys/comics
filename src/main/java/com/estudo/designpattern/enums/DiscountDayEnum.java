package com.estudo.designpattern.enums;


public enum DiscountDayEnum {

    MONDAY(1, "Segunda-feira"), TUESDAY(2, "Terça-feira"), WEDNESDAY(3, "Quarta-feira"), THURSDAY(4, "Quinta-feira"), FRIDAY(5, "Sexta-feira"), SATURDAY(6, "Sábado"), SUNDAY(7, "Domingo");

    private int code;
    private String label;

    private DiscountDayEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
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