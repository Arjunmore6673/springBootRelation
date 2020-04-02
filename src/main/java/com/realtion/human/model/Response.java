package com.realtion.human.model;

import org.springframework.stereotype.Component;

@Component
public class Response {

    private boolean success = true;
    private String message = "success";
    private Object data;

    public void successResponse(Object data) {
        this.success = true;
        this.data = data;
    }

    public void successResponse(String message, Object data) {
        this.success = true;
        this.message = message;
        this.data = data;
    }

    public void errorResponse(String message) {
        this.success = false;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
