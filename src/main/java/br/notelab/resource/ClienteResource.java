package br.notelab.resource;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.JsonWebToken;

import br.notelab.dto.pessoa.cliente.ClienteDTO;
import br.notelab.dto.pessoa.usuario.EmailPatchDTO;
import br.notelab.dto.pessoa.usuario.SenhaPatchDTO;
import br.notelab.service.pessoa.cliente.ClienteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    public ClienteService clienteService;

    @GET
    @RolesAllowed({"Funcionario", "Adm"})
    public Response findAll(){ 
        return Response.ok(clienteService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(clienteService.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        return Response.ok(clienteService.findByNome(nome)).build();
    }

    @GET
    @Path("/search/cpf/{cpf}")
    public Response findByCpf(@PathParam("cpf") String cpf){
        return Response.ok(clienteService.findByCpf(cpf)).build();
    }

    @POST
    public Response create(ClienteDTO dto){
        return Response
            .status(201)
            .entity(clienteService.create(dto))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ClienteDTO dto){
        clienteService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        clienteService.delete(id);
        return Response.status(204).build();
    }

    @PATCH
    @Path("/{id}/email")
    public Response updateEmail(@PathParam("id") Long id, EmailPatchDTO dto){
        clienteService.updateEmail(id, dto);
        
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/{id}/senha")
    public Response updateSenha(@PathParam("id") Long id, SenhaPatchDTO dto){
        clienteService.updateSenha(id, dto);
        
        return Response.status(Status.NO_CONTENT).build();
    }
}