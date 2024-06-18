package br.notelab.resource;

import org.jboss.logging.Logger;

import br.notelab.dto.pessoa.funcionario.FuncionarioDTO;
import br.notelab.dto.pessoa.usuario.EmailPatchDTO;
import br.notelab.dto.pessoa.usuario.SenhaPatchDTO;
import br.notelab.service.pessoa.funcionario.FuncionarioService;
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

@Path("/funcionarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuncionarioResource {

    private static final Logger LOG = Logger.getLogger(FuncionarioResource.class);
    
    @Inject
    public FuncionarioService funcionarioService;

    @GET
    @RolesAllowed({"Funcionario"})
    public Response findAll(){
        LOG.infov("Buscando todos os funcionários");
        return Response.ok(funcionarioService.findAll()).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        LOG.infof("Buscando funcionário com id %d", id);
        return Response.ok(funcionarioService.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        LOG.infof("Buscando todos os funcionários com nome %s", nome);
        return Response.ok(funcionarioService.findByNome(nome)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/cpf/{cpf}")
    public Response findByCpf(@PathParam("cpf") String cpf){
        LOG.infof("Buscando todos os funcionários com cpf %s", cpf);
        return Response.ok(funcionarioService.findByCpf(cpf)).build();
    }

    @POST
    @RolesAllowed({"Funcionario"})
    public Response create(FuncionarioDTO dto){
        LOG.infov("Criando funcionário");
        return Response
            .status(201)
            .entity(funcionarioService.create(dto))
            .build();
    }

    @PUT
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, FuncionarioDTO dto){
        LOG.infof("Atualizando funcionário com id %d", id);

        funcionarioService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        LOG.infof("Removendo funcionário com id %d", id);

        funcionarioService.delete(id);
        return Response.status(204).build();
    }

    @PATCH
    @RolesAllowed({"Funcionario"})
    @Path("/{id}/email")
    public Response updateEmail(@PathParam("id") Long id, EmailPatchDTO dto){
        LOG.infof("Atualizando email do funcionário com id %d", id);

        funcionarioService.updateEmail(id, dto);        
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed({"Funcionario"})
    @Path("/{id}/senha")
    public Response updateSenha(@PathParam("id") Long id, SenhaPatchDTO dto){
        LOG.infof("Atualizando senha do funcionário com id %d", id);

        funcionarioService.updateSenha(id, dto);        
        return Response.status(Status.NO_CONTENT).build();
    }
}
