package br.notelab.resource;

import br.notelab.dto.notebook.NotebookDTO;
import br.notelab.service.NotebookService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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
import jakarta.ws.rs.core.Response.Status;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/notebooks")
public class NotebookResource {
    
    @Inject
    public NotebookService notebookService;

    @GET
    public Response findAll(){
        return Response.ok(notebookService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(notebookService.findById(id)).build();
    }

    @GET
    @Path("/search/{descricao}")
    public Response findByDescricao(@PathParam("descricao") String descricao){
        return Response.ok(notebookService.findByDescricao(descricao)).build();
    }

    @POST
    public Response create(@Valid NotebookDTO dto){
        return Response.status(Status.CREATED).entity(notebookService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, NotebookDTO dto){
        notebookService.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        notebookService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
}
