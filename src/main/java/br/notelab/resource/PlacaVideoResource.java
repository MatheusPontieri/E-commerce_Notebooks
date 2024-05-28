package br.notelab.resource;

import br.notelab.dto.notebook.gpu.PlacaVideoDTO;
import br.notelab.service.notebook.gpu.PlacaVideoService;
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

@Path("/placas-video")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlacaVideoResource {
    @Inject
    public PlacaVideoService placaVideoService;

    @GET
    public Response findAll(){
        return Response.ok(placaVideoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(placaVideoService.findById(id)).build();
    }

    @GET
    @Path("/search/modelo/{modelo}")
    public Response findByModelo(@PathParam("modelo") String modelo){
        return Response.ok(placaVideoService.findByModelo(modelo)).build();
    }

    @GET
    @Path("/search/memoria/{memoria}")
    public Response findByMemoriaVideo(@PathParam("memoria") String memoria){
        return Response.ok(placaVideoService.findByMemoriaVideo(memoria)).build();
    }

    @POST
    public Response create(PlacaVideoDTO dto){
        return Response
            .status(201)
            .entity(placaVideoService.create(dto))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, PlacaVideoDTO dto){
        placaVideoService.update(id, dto);

        return Response.status(204).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        placaVideoService.delete(id);
        
        return Response.status(204).build();
    }
}
