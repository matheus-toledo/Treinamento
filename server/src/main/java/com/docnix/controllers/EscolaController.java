package com.docnix.controllers;


import com.docnix.entity.Escola;
import com.docnix.errorHandler.ErrorObject;
import com.docnix.exceptionMapper.NotFoundException;
import com.docnix.exceptionMapper.RegraDeNegocioException;
import com.docnix.exceptionMapper.ServerException;
import com.docnix.service.EscolaService;
import org.hibernate.exception.ConstraintViolationException;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/escolas")
public class EscolaController {
    private static final EscolaService escolaService = new EscolaService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEscolas() throws ServerException {
        return Response.ok().entity(escolaService.listar()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEscola(@PathParam("id") Long id) throws NotFoundException, ServerException {
        return Response.ok().entity(escolaService.obter(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirEscola(Escola escola) throws RegraDeNegocioException {
        if (escola.getDiretor() == null) {
            return Response.status(400).build();
        }

        if (escola.getNome().length() > 255) {
            escola.setNome(escola.getNome().substring(0, 254));
        }

        if (escola.getDescricao().length() > 300) {
            escola.setDescricao(escola.getDescricao().substring(0, 299));
        }

        return Response.status(Response.Status.CREATED).entity(escolaService.inserir(escola)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarEscola(Escola escola) throws RegraDeNegocioException {
        return Response.ok().entity(escolaService.editar(escola)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarEscola(@PathParam("id") Long id) throws ServerException {

        try {
            escolaService.deletar(id);
            return Response.ok().build();

        } catch (Exception ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                return Response.serverError().entity(new ErrorObject("A escola não pode ser deletada pois está sendo usada em outro lugar!")).build();
            } else {
                throw new ServerException();
            }
        }
    }
}
