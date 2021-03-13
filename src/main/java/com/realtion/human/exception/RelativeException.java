package com.realtion.human.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RelativeException extends  RuntimeException {

    String message;
    HttpStatus responseStatus;

    public RelativeException(String message, HttpStatus responseStatus) {
        this.message=message;
        this.responseStatus=responseStatus;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(HttpStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
