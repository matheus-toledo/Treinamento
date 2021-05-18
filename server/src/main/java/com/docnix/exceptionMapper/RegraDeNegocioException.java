package com.docnix.exceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RegraDeNegocioException extends Exception implements ExceptionMapper<RegraDeNegocioException> {
    public RegraDeNegocioException(){

    }

    public RegraDeNegocioException(String message) {
        super(message);
    }

    @Override
    public Response toResponse(RegraDeNegocioException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
