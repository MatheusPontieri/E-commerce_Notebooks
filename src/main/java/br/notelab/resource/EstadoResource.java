package br.notelab.resource;

import br.notelab.dto.endereco.estado.EstadoDTO;
import br.notelab.service.endereco.estado.EstadoService;
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

@Path("/estados")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadoResource {
    @Inject
    EstadoService estadoService;

    @GET
    @RolesAllowed({"Funcionario"})
    public Response findAll(){
        return Response.ok(estadoService.findAll()).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(estadoService.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        return Response.ok(estadoService.findByNome(nome)).build();
    }

    @POST
    @RolesAllowed({"Funcionario"})
    public Response create(EstadoDTO dto){
        return Response
            .status(201)
            .entity(estadoService.create(dto))
            .build();
    }

    @PUT
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, EstadoDTO dto){
        estadoService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        estadoService.delete(id);
        return Response.status(204).build();
    }
}
