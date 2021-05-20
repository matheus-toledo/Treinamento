package com.docnix.controllers;

import com.docnix.entity.Aluno;
import com.docnix.errorHandler.ErrorObject;
import com.docnix.exceptionMapper.NotFoundException;
import com.docnix.exceptionMapper.RegraDeNegocioException;
import com.docnix.exceptionMapper.ServerException;
import com.docnix.service.AlunoService;
import org.hibernate.exception.ConstraintViolationException;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/alunos")
public class AlunoController {
    private static final AlunoService alunoService = new AlunoService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlunos() throws ServerException {
        return Response.ok().entity(alunoService.listar()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAluno(@PathParam("id") Long id) throws NotFoundException, ServerException {
        return Response.ok().entity(alunoService.obter(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirAluno(Aluno aluno) throws RegraDeNegocioException, ServerException {
        return Response.status(Response.Status.CREATED).entity(alunoService.inserir(aluno)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraAluno(Aluno aluno) throws RegraDeNegocioException, ServerException {
        return Response.ok().entity(alunoService.editar(aluno)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaAluno(@PathParam("id") Long id) throws ServerException {
        try {
            alunoService.deletar(id);
            return Response.ok().build();
        } catch (Exception ex) {
            if(ex.getCause() instanceof ConstraintViolationException){
                return Response.status(500).entity(new ErrorObject("O aluno não pode ser deletado pois está sendo usado em outro lugar")).build();
            }
            else {
              throw new ServerException();
            }
        }
    }
}
