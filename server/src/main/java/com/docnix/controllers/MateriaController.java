package com.docnix.controllers;


import com.docnix.entity.Materia;

import com.docnix.service.MateriaService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/materias")
public class MateriaController {
    private static final MateriaService materiaService = new MateriaService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarios() {
        return Response.ok().entity(materiaService.listar()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@PathParam("id") Long id) {
        return Response.ok().entity(materiaService.obter(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirUsuario(Materia materia) {

        return Response.status(Response.Status.CREATED).entity(materiaService.inserir(materia)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraUsuario(Materia materia) {

        return Response.ok().entity(materiaService.editar(materia)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaUsuario(@PathParam("id") Long id) {
        materiaService.deletar(id);
        return Response.ok().build();
    }

}
