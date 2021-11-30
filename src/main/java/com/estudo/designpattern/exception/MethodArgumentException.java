package com.estudo.designpattern.exception;

public class MethodArgumentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MethodArgumentException(String msg) {
        super(msg);
    }

}