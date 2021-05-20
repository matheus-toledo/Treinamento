package com.docnix.controllers;

import com.docnix.entity.Turma;
import com.docnix.errorHandler.ErrorObject;
import com.docnix.exceptionMapper.NotFoundException;
import com.docnix.exceptionMapper.RegraDeNegocioException;
import com.docnix.exceptionMapper.ServerException;
import com.docnix.service.TurmaService;
import org.hibernate.exception.ConstraintViolationException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/turmas")
public class TurmaController {
    private static final TurmaService turmaService = new TurmaService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTurmas() throws ServerException {
        return Response.ok().entity(turmaService.listar()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTurma(@PathParam("id") Long id) throws ServerException, NotFoundException {
        return Response.ok().entity(turmaService.obter(id)).build();
    }

    @GET
    @Path("/sequencia/{sigla}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMatricula(@QueryParam("id") Long id, @PathParam("sigla") String sigla) {
        return Response.ok().entity(turmaService.gerarMatricula(sigla, id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirTurma(Turma turma) throws ServerException, RegraDeNegocioException {
        Turma result = turmaService.inserir(turma);

        return Response.status(Response.Status.CREATED).entity(new Turma(result.getId())).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraTurma(Turma turma) throws ServerException, RegraDeNegocioException {
        return Response.ok().entity(turmaService.editar(turma)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaTurma(@PathParam("id") Long id) throws ServerException {
        try {
            turmaService.deletar(id);
            return Response.ok().build();
        }catch (Exception ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                return Response.status(500).entity(new ErrorObject("A turma não pode ser deletada pois está sendo usada em outro lugar")).build();
            } else {
                throw new ServerException();
            }
        }
    }
}
