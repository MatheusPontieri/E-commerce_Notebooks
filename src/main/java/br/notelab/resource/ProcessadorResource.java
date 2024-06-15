package br.notelab.resource;

import br.notelab.dto.notebook.processador.ProcessadorDTO;
import br.notelab.service.notebook.processador.ProcessadorService;
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

@Path("/processadores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProcessadorResource {
    @Inject
    public ProcessadorService processadorService;

    @GET
    @RolesAllowed({"Funcionario"})
    public Response findAll(){
        return Response.ok(processadorService.findAll()).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(processadorService.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/{modelo}")
    public Response findByModelo(@PathParam("modelo") String modelo){
        return Response.ok(processadorService.findByModelo(modelo)).build();
    }

    @POST
    @RolesAllowed({"Funcionario"})
    public Response create(ProcessadorDTO dto){
        return Response
            .status(201)
            .entity(processadorService.create(dto))
            .build();
    }

    @PUT
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ProcessadorDTO dto){
        processadorService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        processadorService.delete(id);
        return Response.status(204).build();
    }
}
