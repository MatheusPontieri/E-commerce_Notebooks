package br.notelab.resource;

import br.notelab.dto.notebook.memoria.ArmazenamentoDTO;
import br.notelab.service.notebook.memoria.ArmazenamentoService;
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

@Path("/armazenamentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArmazenamentoResource {

    @Inject
    public ArmazenamentoService armazenamentoService;

    @GET
    @RolesAllowed("Funcionario")
    public Response findAll(){
        return Response.ok(armazenamentoService.findAll()).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(armazenamentoService.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        return Response.ok(armazenamentoService.findByNome(nome)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/capacidade/{capacidade}")
    public Response findByCapacidade(@PathParam("capacidade") String capacidade){
        return Response.ok(armazenamentoService.findByCapacidade(capacidade)).build();
    }

    @POST
    @RolesAllowed({"Funcionario"})
    public Response create(ArmazenamentoDTO dto){
        return Response
            .status(201)
            .entity(armazenamentoService.create(dto))
            .build();
    }

    @PUT
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ArmazenamentoDTO dto){
        armazenamentoService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        armazenamentoService.delete(id);
        return Response.status(204).build();
    }
}