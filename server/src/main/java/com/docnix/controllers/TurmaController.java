package com.docnix.controllers;

import com.docnix.entity.Turma;
import com.docnix.service.TurmaService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/turmas")
public class TurmaController {
    private static final TurmaService turmaService = new TurmaService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTurmas() {
        return Response.ok().entity(turmaService.listar()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTurma(@PathParam("id") Long id) {
        return Response.ok().entity(turmaService.obter(id)).build();
    }

    @GET
    @Path("/sequencia")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSequencias(){
        return Response.accepted(turmaService.listarSequencias()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirTurma(Turma turma) {
        return Response.status(Response.Status.CREATED).entity(turmaService.inserir(turma)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraTurma(Turma turma) {
        return Response.ok().entity(turmaService.editar(turma)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaTurma(@PathParam("id") Long id) {
        turmaService.deletar(id);
        return Response.ok().build();
    }
}
