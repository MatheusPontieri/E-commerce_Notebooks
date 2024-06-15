package br.notelab.resource;

import br.notelab.dto.notebook.recurso.RecursoDTO;
import br.notelab.service.notebook.recurso.RecursoService;
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

@Path("/recursos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecursoResource {
    @Inject
    public RecursoService recursoService;

    @GET
    @RolesAllowed({"Funcionario"})
    public Response findAll(){
        return Response.ok(recursoService.findAll()).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(recursoService.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        return Response.ok(recursoService.findByNome(nome)).build();
    }

    @POST
    @RolesAllowed({"Funcionario"})
    public Response create(RecursoDTO dto){
        return Response
            .status(201)
            .entity(recursoService.create(dto))
            .build();
    }

    @PUT
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, RecursoDTO dto){
        recursoService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        recursoService.delete(id);
        return Response.status(204).build();
    }
}