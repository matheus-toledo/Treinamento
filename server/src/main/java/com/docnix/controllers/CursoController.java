package com.docnix.controllers;

import com.docnix.entity.Curso;

import com.docnix.service.CursoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cursos")
public class CursoController {
    private static final CursoService cursoService = new CursoService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCursos() {
        return Response.ok().entity(cursoService.listar()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurso(@PathParam("id") Long id) {
        return Response.ok().entity(cursoService.obter(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirCurso(Curso curso) {

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
    public Response alteraCurso(Curso curso) {

        return Response.ok().entity(cursoService.editar(curso)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaCurso(@PathParam("id") Long id) {
        cursoService.deletar(id);
        return Response.ok().build();
    }

}

