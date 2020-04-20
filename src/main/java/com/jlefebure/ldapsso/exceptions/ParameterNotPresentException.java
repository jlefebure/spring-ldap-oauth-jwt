package com.jlefebure.ldapsso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParameterNotPresentException extends RuntimeException {
    public ParameterNotPresentException() {
    }

    public ParameterNotPresentException(String message) {
        super(message);
    }

    public ParameterNotPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterNotPresentException(Throwable cause) {
        super(cause);
    }

    public ParameterNotPresentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
