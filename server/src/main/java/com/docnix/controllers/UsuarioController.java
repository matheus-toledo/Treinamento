package com.docnix.controllers;


import com.docnix.entity.Usuario;
import com.docnix.errorHandler.ErrorObject;
import com.docnix.exceptionMapper.RegraDeNegocioException;
import com.docnix.exceptionMapper.ServerException;
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
    public Response getUsuarios() throws ServerException {
        return Response.ok().entity(usuarioService.listar()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@PathParam("id") Long id) throws ServerException, RegraDeNegocioException {
        return Response.ok().entity(usuarioService.obter(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirUsuario(Usuario usuario) throws ServerException, RegraDeNegocioException {
        return Response.status(Response.Status.CREATED).entity(usuarioService.inserir(usuario)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraUsuario(Usuario usuario) throws ServerException, RegraDeNegocioException {
        return Response.ok().entity(usuarioService.editar(usuario)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaUsuario(@PathParam("id") Long id) throws ServerException {
        try{
            usuarioService.deletar(id);
            return Response.ok().build();
        } catch (Exception ex){
            if(ex.getCause() instanceof ConstraintViolationException){
                return Response.serverError().entity(new ErrorObject("Esse usuário não pode ser deletado pois está sendo usado em outro lugar!")).build();
            }
            else {
                throw new ServerException();
            }
        }
    }
}
