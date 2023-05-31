package com.gutosoethe.exception;

import java.io.Serializable;

public class JavaeeExceptions extends  Exception implements Serializable {
    private static final long serialVersionUID = 8203160489287447123L;

    public JavaeeExceptions() {
        super();
    }

    public JavaeeExceptions(String message) {
        super(message);
    }

    public JavaeeExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
