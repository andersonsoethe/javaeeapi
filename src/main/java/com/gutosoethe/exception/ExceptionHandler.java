package com.gutosoethe.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gutosoethe.annotation.ResponseStatus;


@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public Response toResponse(Exception exception) {
        LOGGER.error(exception.getMessage(), exception);

        int status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        if (exception.getClass().isAnnotationPresent(ResponseStatus.class)){
            status = exception.getClass().getAnnotation(ResponseStatus.class).value();
        }
        return Response.status(status).entity(exception.getMessage()).build();
    }
}
