package br.notelab.resource;

import br.notelab.dto.notebook.conexao.ConexaoSemFioDTO;
import br.notelab.service.notebook.conexao.ConexaoSemFioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/conexoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConexaoSemFioResource {

    @Inject
    public ConexaoSemFioService conexaoSemFioService;

    @GET
    @RolesAllowed({"Funcionario"})
    public Response findAll(){
        return Response.ok(conexaoSemFioService.findAll()).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(conexaoSemFioService.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        return Response.ok(conexaoSemFioService.findByNome(nome)).build();
    }

    @POST
    @RolesAllowed({"Funcionario"})
    public Response create(ConexaoSemFioDTO dto){
        return Response
            .status(201)
            .entity(conexaoSemFioService.create(dto))
            .build();
    }

    @PUT
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ConexaoSemFioDTO dto){
        conexaoSemFioService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        conexaoSemFioService.delete(id);
        return Response.status(204).build();
    }
}