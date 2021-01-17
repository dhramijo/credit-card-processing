package com.publicis.cardprocessing.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Declares methods to return errors and other messages from the API.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiError {

    private final String message;
    private final List<String> errors;
    private final HttpStatus httpStatus;

    ApiError(String message, List<String> errors, HttpStatus httpStatus) {
        this.message = message;
        this.errors = errors;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
