package com.docnix.exceptionMapper;

import com.docnix.errorHandler.ErrorObject;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Optional;

@Provider
public class RegraDeNegocioException extends Exception implements ExceptionMapper<RegraDeNegocioException> {
    private Integer status;
    public RegraDeNegocioException(){

    }

    public RegraDeNegocioException(String message) {
        super(message);
    }

    public RegraDeNegocioException(String message, Integer status) {
        super(message);
        this.status = status;
    }

    @Override
    public Response toResponse(RegraDeNegocioException exception) {



        if (Optional.ofNullable(exception.status).isPresent()){
            if (exception.status == 404){
                return Response.status(Response.Status.NOT_FOUND).entity(new ErrorObject(exception.getMessage())).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorObject(exception.getMessage())).build();
    }
}
