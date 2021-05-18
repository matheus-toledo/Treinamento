package com.docnix.exceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServerException extends Throwable implements ExceptionMapper<Throwable> {
    public ServerException() {
        super("Erro interno do Servidor! Contate o suporte!");
    }

    @Override
    public Response toResponse(Throwable exception) {
        return Response.serverError().entity(exception.getMessage()).build();
    }
}
