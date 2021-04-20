package com.docnix.controllers;


import com.docnix.entity.Escola;
import com.docnix.service.EscolaService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/escolas")
public class EscolaController {
    private static final EscolaService escolaService = new EscolaService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEscolas() {
        return Response.ok().entity(escolaService.listar()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEscola(@PathParam("id") Long id) {
        return Response.ok().entity(escolaService.obter(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirEscola(Escola escola) {
        if (escola.getDiretor()==null){
            return Response.status(400).build();
        }

        if (escola.getNome().length()>255){
            escola.setNome(escola.getNome().substring(0,254));
        }

        if (escola.getDescricao().length()>300){
            escola.setDescricao(escola.getDescricao().substring(0,299));
        }

        try{
            return Response.status(Response.Status.CREATED).entity(escolaService.inserir(escola)).build();
        } catch (Exception ex){
            return Response.serverError().build();
        }

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarEscola(Escola escola) {
        return Response.ok().entity(escolaService.editar(escola)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarEscola(@PathParam("id") Long id) {
        escolaService.deletar(id);
        return Response.ok().build();
    }

}
