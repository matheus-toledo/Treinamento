package com.docnix.controllers;


import com.docnix.entity.Usuario;
import com.docnix.errorHandler.ErrorMessage;
import com.docnix.service.UsuarioService;
import org.hibernate.exception.ConstraintViolationException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios")
public class UsuarioController {
    private static final UsuarioService usuarioService = new UsuarioService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarios() {
        return Response.ok().entity(usuarioService.listar()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@PathParam("id") Long id) {
        return Response.ok().entity(usuarioService.obter(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirUsuario(Usuario usuario) {

        return Response.status(Response.Status.CREATED).entity(usuarioService.inserir(usuario)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraUsuario(Usuario usuario) {


        return Response.ok().entity(usuarioService.editar(usuario)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaUsuario(@PathParam("id") Long id) {
        try{
        usuarioService.deletar(id);
        return Response.ok().build();
        }catch (Exception ex){
            ErrorMessage errorMessage = new ErrorMessage();
            if(ex.getCause() instanceof ConstraintViolationException){
                errorMessage.setErrorMessage("O usuário não pode ser deletado pois existe um curso, matéria ou escola utilizando esse usuário");
            } else {
                errorMessage.setErrorMessage("Erro ao deletar usuário");
            }
            return Response.serverError().entity(errorMessage).build();


        }
    }

}
