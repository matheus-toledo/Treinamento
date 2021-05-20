package com.docnix.exceptionMapper;

import com.docnix.errorHandler.ErrorObject;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Optional;

@Provider
public class RegraDeNegocioException extends Exception implements ExceptionMapper<RegraDeNegocioException> {
    public RegraDeNegocioException(){

    }

    public RegraDeNegocioException(String message) {
        super(message);
    }

    @Override
    public Response toResponse(RegraDeNegocioException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorObject(exception.getMessage())).build();
    }
}
