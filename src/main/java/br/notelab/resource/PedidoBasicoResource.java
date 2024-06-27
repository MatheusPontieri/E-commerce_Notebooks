package br.notelab.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.notelab.dto.pedido.PedidoDTO;
import br.notelab.service.pedido.PedidoService;
import br.notelab.service.pessoa.cliente.ClienteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/pedido-basico")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoBasicoResource {

    private static final Logger LOG = Logger.getLogger(PedidoBasicoResource.class);

    @Inject
    ClienteService clienteService;

    @Inject
    PedidoService pedidoService;

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed("Cliente")
    public Response findPedidos(){
        String email = jwt.getSubject();
        Long id = clienteService.findByEmail(email).id();

        return Response.ok(pedidoService.findByCliente(id)).build();
    }

    @POST
    @RolesAllowed("Cliente")
    public Response create(PedidoDTO dto){
        String email = jwt.getSubject();
        Long id = clienteService.findByEmail(email).id();

        return Response.status(Status.CREATED).entity(pedidoService.create(dto, id)).build();
    }
}