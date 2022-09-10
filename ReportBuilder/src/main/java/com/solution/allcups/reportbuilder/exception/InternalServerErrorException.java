package com.solution.allcups.reportbuilder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends Exception {
    public InternalServerErrorException() {
        super(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}
