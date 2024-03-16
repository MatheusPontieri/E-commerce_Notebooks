package br.notelab.resource;

import br.notelab.dto.notebook.ProcessadorDTO;
import br.notelab.service.ProcessadorService;
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
@Path("/processadores")
public class ProcessadorResource {

    @Inject
    public ProcessadorService processadorService;

    @GET
    public Response findAll(){
        return Response.ok(processadorService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(processadorService.findById(id)).build();
    }

    @GET
    @Path("/search/modelo/{modelo}")
    public Response findByModelo(String modelo){
        return Response.ok(processadorService.findByModelo(modelo)).build();
    }


    @GET
    @Path("/search/modelo/{geracao}")
    public Response findByGeracao(String geracao){
        return Response.ok(processadorService.findByGeracao(geracao)).build();
    }

    @POST
    public Response create(@Valid ProcessadorDTO dto){
        return Response.status(Status.CREATED).entity(processadorService.create(dto)).build();
    }

   @PUT
   @Path("/{id}")
   public Response update(@PathParam("id") Long id, ProcessadorDTO dto){
        processadorService.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

   @DELETE
   @Path("/{id}")
   public Response delete(@PathParam("id") Long id){
        processadorService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
   }
}