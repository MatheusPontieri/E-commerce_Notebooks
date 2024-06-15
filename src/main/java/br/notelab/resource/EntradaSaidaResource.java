package br.notelab.resource;

import br.notelab.dto.notebook.conexao.EntradaSaidaDTO;
import br.notelab.service.notebook.conexao.EntradaSaidaService;
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

@Path("/entradas_saidas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EntradaSaidaResource {

    @Inject
    public EntradaSaidaService entradaSaidaService;

    @GET
    @RolesAllowed({"Funcionario"})
    public Response findAll(){
        return Response.ok(entradaSaidaService.findAll()).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(entradaSaidaService.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        return Response.ok(entradaSaidaService.findByNome(nome)).build();
    }

    @POST
    @RolesAllowed({"Funcionario"})
    public Response create(EntradaSaidaDTO dto){
        return Response
            .status(201)
            .entity(entradaSaidaService.create(dto))
            .build();
    }

    @PUT
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, EntradaSaidaDTO dto){
        entradaSaidaService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        entradaSaidaService.delete(id);
        return Response.status(204).build();
    }
}