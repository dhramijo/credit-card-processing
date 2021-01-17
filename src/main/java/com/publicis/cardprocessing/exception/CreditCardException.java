package com.publicis.cardprocessing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid Credit Card number")
public class CreditCardException extends RuntimeException {

    public CreditCardException() {
    }

    public CreditCardException(String message) {
        super(message);
    }
}