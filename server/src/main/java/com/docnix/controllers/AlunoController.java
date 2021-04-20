package com.docnix.controllers;

import com.docnix.entity.Aluno;
import com.docnix.service.AlunoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/alunos")
public class AlunoController {
    private static final AlunoService alunoService = new AlunoService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlunos() {
        return Response.ok().entity(alunoService.listar()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAluno(@PathParam("id") Long id) {
        return Response.ok().entity(alunoService.obter(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirAluno(Aluno aluno) {
        return Response.status(Response.Status.CREATED).entity(alunoService.inserir(aluno)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraAluno(Aluno aluno) {
        return Response.ok().entity(alunoService.editar(aluno)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaAluno(@PathParam("id") Long id) {
        alunoService.deletar(id);
        return Response.ok().build();
    }

}
