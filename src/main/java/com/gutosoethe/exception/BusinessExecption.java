package com.gutosoethe.exception;

import com.gutosoethe.annotation.ReponseStatus;

@ReponseStatus(404)
public class BusinessExecption extends RuntimeException{

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
