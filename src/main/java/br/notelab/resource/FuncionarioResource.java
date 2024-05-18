package br.notelab.resource;

import br.notelab.dto.pessoa.funcionario.FuncionarioDTO;
import br.notelab.service.pessoa.funcionario.FuncionarioService;
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

@Path("/funcionarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuncionarioResource {
    
    @Inject
    public FuncionarioService funcionarioService;

    @GET
    public Response findAll(){
        return Response.ok(funcionarioService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(funcionarioService.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        return Response.ok(funcionarioService.findByNome(nome)).build();
    }

    @GET
    @Path("/search/cpf/{cpf}")
    public Response findByCpf(@PathParam("cpf") String cpf){
        return Response.ok(funcionarioService.findByCpf(cpf)).build();
    }

    @POST
    public Response create(FuncionarioDTO dto){
        return Response
            .status(201)
            .entity(funcionarioService.create(dto))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, FuncionarioDTO dto){
        funcionarioService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        funcionarioService.delete(id);
        return Response.status(204).build();
    }
}
