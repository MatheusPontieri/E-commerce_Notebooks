package br.notelab.resource;

import br.notelab.dto.notebook.memoria.MemoriaRamDTO;
import br.notelab.service.notebook.memoria.MemoriaRamService;
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

@Path("/memorias_ram")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MemoriaRamResource {

    @Inject
    MemoriaRamService memoriaRamService;

    @GET
    @RolesAllowed({"Funcionario"})
    public Response findAll(){
        return Response.ok(memoriaRamService.findAll()).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(memoriaRamService.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/{capacidade}")
    public Response findByCapacidade(@PathParam("capacidade") String capacidade){
        return Response.ok(memoriaRamService.findByCapacidade(capacidade)).build();
    }

    @POST
    @RolesAllowed({"Funcionario"})
    public Response create(MemoriaRamDTO dto){
        return Response
            .status(201)
            .entity(memoriaRamService.create(dto))
            .build();
    }

    @PUT
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, MemoriaRamDTO dto){
        memoriaRamService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        memoriaRamService.delete(id);
        return Response.status(204).build();
    }
}