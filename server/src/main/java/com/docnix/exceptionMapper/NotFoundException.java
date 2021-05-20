package com.docnix.exceptionMapper;

import com.docnix.errorHandler.ErrorObject;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundException extends Exception implements ExceptionMapper<NotFoundException> {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public Response toResponse(NotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorObject(exception.getMessage())).build();
    }
}
