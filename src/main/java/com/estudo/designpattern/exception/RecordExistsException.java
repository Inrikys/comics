package com.estudo.designpattern.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class RecordExistsException extends HttpStatusCodeException {

    public RecordExistsException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

}
