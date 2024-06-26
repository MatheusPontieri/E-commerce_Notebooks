package br.notelab.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.notelab.dto.pessoa.cliente.ClienteDTO;
import br.notelab.dto.pessoa.usuario.EmailPatchDTO;
import br.notelab.dto.pessoa.usuario.SenhaPatchDTO;
import br.notelab.form.ImageForm;
import br.notelab.service.pessoa.PessoaFileServiceImpl;
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
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/clientes_basicos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteBasicoResource {

    private static final Logger LOG = Logger.getLogger(ClienteBasicoResource.class);

    @Inject
    ClienteService clienteService;

    @Inject 
    PessoaFileServiceImpl fileService;
    
    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed("Cliente")
    public Response getDados(){
        String email = jwt.getSubject();

        return Response.ok(clienteService.findByEmail(email)).build();
    }

    @POST
    public Response create(ClienteDTO dto){
        return Response.status(201).entity(clienteService.create(dto)).build();
    }

    @PUT
    @RolesAllowed({"Cliente"})
    public Response update(ClienteDTO dto){
        String email = jwt.getSubject();
        clienteService.update(clienteService.findByEmail(email).id(), dto);

        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @RolesAllowed({"Cliente"})
    public Response delete(){
        String email = jwt.getSubject();
        clienteService.delete(clienteService.findByEmail(email).id());

        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed({"Cliente"})
    @Path("/email")
    public Response updateEmail(EmailPatchDTO dto){
        String email = jwt.getSubject();
        clienteService.updateEmail(clienteService.findByEmail(email).id(), dto);
        
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed({"Cliente"})
    @Path("/senha")
    public Response updateSenha(SenhaPatchDTO dto){
        String email = jwt.getSubject();
        clienteService.updateSenha(clienteService.findByEmail(email).id(), dto);
        
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed({"Cliente"})
    @Path("/inclusao-item-desejo/{id}")
    public Response adicionarItemDesejo(@PathParam("id-") Long id){
        String email = jwt.getSubject();
        clienteService.adicionarItemDesejo(clienteService.findByEmail(email).id(), id);

        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed({"Cliente"})
    @Path("/exclusao-item-desejo/{id}")
    public Response removerItemDesejo(@PathParam("id") Long idItem){
        String email = jwt.getSubject();
        clienteService.removerItemDesejo(clienteService.findByEmail(email).id(), idItem);

        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/image/upload")
    @RolesAllowed({"Cliente"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@MultipartForm ImageForm form){
        String email = jwt.getSubject();
        Long id = clienteService.findByEmail(email).id();

        LOG.infof("Upload de imagem para cliente com id %d", id);
        fileService.upload(id, form.getNomeImagem(), form.getImagem());

        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/image/download/{nomeImagem}")
    @RolesAllowed({"Cliente"})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem){
        LOG.infof("Download da imagem %s", nomeImagem);

        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachement: filename=" + nomeImagem);
        
        return response.build();
    }
}