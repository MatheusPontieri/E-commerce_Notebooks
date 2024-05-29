package br.notelab.resource;

import br.notelab.dto.endereco.cidade.CidadeDTO;
import br.notelab.dto.pedido.cupom.CupomDTO;
import br.notelab.service.pedido.cupom.CupomService;
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

@Path("/cupons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CupomResource {
    @Inject
    public CupomService cupomService;

    @GET
    public Response findAll(){
        return Response.ok(cupomService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(cupomService.findById(id)).build();
    }

    @GET
    @Path("/search/codigo/{codigo}")
    public Response findByCodigo(@PathParam("codigo") String codigo){
        return Response.ok(cupomService.findByCodigo(codigo)).build();
    }

    @GET
    @Path("/search/fornecedor/{idFornecedor}")
    public Response findByFornecedor(@PathParam("idFornecedor") Long idFornecedor){
        return Response.ok(cupomService.findByFornecedor(idFornecedor)).build();
    }

    @POST
    public Response create(CupomDTO dto){
        return Response
            .status(201)
            .entity(cupomService.create(dto))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, CupomDTO dto){
        cupomService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        cupomService.delete(id);
        return Response.status(204).build();
    }
}