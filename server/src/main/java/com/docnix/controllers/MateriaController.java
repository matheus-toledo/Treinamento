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
    public Response getMaterias() {
        return Response.ok().entity(materiaService.listar()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMateria(@PathParam("id") Long id) {
        return Response.ok().entity(materiaService.obter(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirMateria(Materia materia) {
        if (materia.getProfessor()==null){
            return Response.status(400).build();
        }

        if (materia.getDescricao().length()>2500){
            materia.setDescricao(materia.getDescricao().substring(0,2499));
        }

        return Response.status(Response.Status.CREATED).entity(materiaService.inserir(materia)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraMateria(Materia materia) {

        return Response.ok().entity(materiaService.editar(materia)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaMateria(@PathParam("id") Long id) {
        materiaService.deletar(id);
        return Response.ok().build();
    }

}
