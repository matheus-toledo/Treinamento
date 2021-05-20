package com.docnix.controllers;

import com.docnix.entity.Curso;

import com.docnix.errorHandler.ErrorObject;
import com.docnix.exceptionMapper.RegraDeNegocioException;
import com.docnix.exceptionMapper.ServerException;
import com.docnix.service.CursoService;
import org.hibernate.exception.ConstraintViolationException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cursos")
public class CursoController {
    private static final CursoService cursoService = new CursoService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCursos() throws ServerException {
        return Response.ok().entity(cursoService.listar()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurso(@PathParam("id") Long id) throws ServerException, RegraDeNegocioException {
        return Response.ok().entity(cursoService.obter(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirCurso(Curso curso) throws RegraDeNegocioException {

        if (curso.getDescricao().length()>2500){
            curso.setDescricao(curso.getDescricao().substring(0,2499));
        }
        if (curso.getSigla().length()>5){
            curso.setSigla(curso.getSigla().substring(0,5));
        }

        return Response.status(Response.Status.CREATED).entity(cursoService.inserir(curso)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraCurso(Curso curso) throws RegraDeNegocioException {

        return Response.ok().entity(cursoService.editar(curso)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaCurso(@PathParam("id") Long id) throws ServerException {
        try{
            cursoService.deletar(id);
            return Response.ok().build();
        }catch (Exception ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                return Response.serverError().entity(new ErrorObject("O curso não pode ser deletado pois está sendo usado em outro lugar!")).build();
            } else {
                throw new ServerException();
            }
        }
    }

}

