package com.teamC.calculation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PROCESSING)
public class PendingException extends RuntimeException {

    public PendingException(String message) {
        super(message);
    }
}

