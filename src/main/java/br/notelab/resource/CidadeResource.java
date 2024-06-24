package br.notelab.resource;

import org.jboss.logging.Logger;

import br.notelab.dto.endereco.cidade.CidadeDTO;
import br.notelab.service.endereco.cidade.CidadeService;
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

@Path("/cidades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CidadeResource {

    @Inject
    public CidadeService cidadeService;
    
    @GET
    @RolesAllowed("Funcionario")
    public Response findAll(){
        return Response.ok(cidadeService.findAll()).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(cidadeService.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/nome_cidade/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        return Response.ok(cidadeService.findByNome(nome)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/nome_estado/{nome}")
    public Response findByNomeEstado(@PathParam("nome") String nome){
        return Response.ok(cidadeService.findByEstado(nome)).build();
    }

    @POST
    @RolesAllowed({"Funcionario"})
    public Response create(CidadeDTO dto){
        return Response
            .status(201)
            .entity(cidadeService.create(dto))
            .build();
    }

    @PUT
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, CidadeDTO dto){
        cidadeService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        cidadeService.delete(id);
        return Response.status(204).build();
    }
}
