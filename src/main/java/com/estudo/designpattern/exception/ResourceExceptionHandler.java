package com.estudo.designpattern.exception;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {

        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgument(MethodArgumentNotValidException e, HttpServletRequest request) {

        String error = "Method Arguments error";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
                request.getRequestURI());

        err.setErrors(e.getAllErrors());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<StandardError> feignException(FeignException e, HttpServletRequest request) {

        String error = "Feign error";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), e.status(), error, e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> elementNoFound(NoSuchElementException e, HttpServletRequest request) {

        String error = "No Such Element error";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardError> nullPointException(NullPointerException e, HttpServletRequest request) {

        String error = "Null Pointer error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<StandardError> dateTimeParseException(DateTimeParseException e, HttpServletRequest request) {

        String error = "Wrong Date Time Format error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(RecordExistsException.class)
    public ResponseEntity<StandardError> recordExistsException(RecordExistsException e, HttpServletRequest request) {

        HttpStatus statusCode = e.getStatusCode();
        String error = "Record doesn't exist";
        StandardError err = new StandardError(Instant.now(), statusCode.value(), error, e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(statusCode).body(err);
    }

}
