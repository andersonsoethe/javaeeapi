package com.gutosoethe.exception;

import com.gutosoethe.annotation.ResponseStatus;

@ResponseStatus(404)
public class BusinessExecption extends RuntimeException{

    private static final long serialVersionUID = 4645676529180479441L;

    public BusinessExecption() {
        super();
    }

    public BusinessExecption(String message) {
        super(message);
    }

    public BusinessExecption(String message, Throwable cause) {
        super(message, cause);
    }
}
