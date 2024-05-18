package br.notelab.resource;

import br.notelab.dto.pessoa.fornecedor.FornecedorDTO;
import br.notelab.service.pessoa.fornecedor.FornecedorService;
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

@Path("/fornecedores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    @Inject
    public FornecedorService fornecedorService;

    @GET
    public Response findAll(){
        return Response.ok(fornecedorService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(fornecedorService.findById(id)).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        return Response.ok(fornecedorService.findByNome(nome)).build();
    }

    @POST
    public Response create(FornecedorDTO dto){
        return Response
            .status(201)
            .entity(fornecedorService.create(dto))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, FornecedorDTO dto){
        fornecedorService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        fornecedorService.delete(id);
        return Response.status(204).build();
    }
}