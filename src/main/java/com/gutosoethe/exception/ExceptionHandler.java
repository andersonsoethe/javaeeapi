package com.gutosoethe.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.gutosoethe.annotation.ResponseStatus;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        int status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        if (exception.getClass().isAnnotationPresent(ResponseStatus.class)){
            status = exception.getClass().getAnnotation(ResponseStatus.class).value();
        }
        return Response.status(status).entity(exception.getMessage()).build();
    }
}
