package com.solution.allcups.reportbuilder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptableException extends Exception {
    public NotAcceptableException() {
        super(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()));
    }
}
