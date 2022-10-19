package com.demo.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotExistBoardException extends RuntimeException{

    public NotExistBoardException(String message) {
        super(message);
    }
}
