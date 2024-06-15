package br.notelab.resource;

import org.jboss.logging.Logger;

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

    private static final Logger LOG = Logger.getLogger(ClienteResource.class);

    @Inject
    public ClienteService clienteService;

    @GET
    @RolesAllowed({"Funcionario"})
    public Response findAll(){ 
        LOG.infov("Buscando todos os clientes");
        return Response.ok(clienteService.findAll()).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        LOG.infov("Buscando cliente com o id {0}", id);
        return Response.ok(clienteService.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        LOG.infov("Buscando cliente pelo nome {0}", nome);
        return Response.ok(clienteService.findByNome(nome)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/cpf/{cpf}")
    public Response findByCpf(@PathParam("cpf") String cpf){
        LOG.infov("Buscando cliente com o cpf {0}", cpf);
        return Response.ok(clienteService.findByCpf(cpf)).build();
    }

    @POST
    public Response create(ClienteDTO dto){
        LOG.infov("Criando cliente");
        return Response
            .status(201)
            .entity(clienteService.create(dto))
            .build();
    }

    @PUT
    @RolesAllowed({"Cliente"})
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ClienteDTO dto){
        LOG.infov("Atualizando cliente com id {0}", id);
        clienteService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @RolesAllowed({"Cliente", "Funcionario"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        LOG.infov("Removendo cliente com id {0}", id);
        clienteService.delete(id);
        return Response.status(204).build();
    }

    @PATCH
    @RolesAllowed({"Cliente"})
    @Path("/{id}/email")
    public Response updateEmail(@PathParam("id") Long id, EmailPatchDTO dto){
        LOG.infov("Atualizando email do cliente com id {0}", id);
        clienteService.updateEmail(id, dto);
        
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed({"Cliente"})
    @Path("/{id}/senha")
    public Response updateSenha(@PathParam("id") Long id, SenhaPatchDTO dto){
        LOG.infov("Atualizando senha do cliente com id {0}", id);
        clienteService.updateSenha(id, dto);
        
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed({"Cliente"})
    @Path("/{id}/inclusao-item-desejo/{id-item}")
    public Response adicionarItemDesejo(@PathParam("id") Long id, @PathParam("id-item") Long idItem){
        LOG.infov("Inserindo item na lista de desejo do cliente com id {0}", id);
        clienteService.adicionarItemDesejo(id, idItem);

        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed({"Cliente"})
    @Path("/{id}/exclusao-item-desejo/{id-item}")
    public Response removerItemDesejo(@PathParam("id") Long id, @PathParam("id-item") Long idItem){
        LOG.infov("Removendo item da lista de desejo do cliente com id {0}", id);
        clienteService.removerItemDesejo(id, idItem);

        return Response.status(Status.NO_CONTENT).build();
    }
}