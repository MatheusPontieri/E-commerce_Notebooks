package br.notelab.resource;

import org.jboss.logging.Logger;

import br.notelab.dto.pagamento.CartaoDTO;
import br.notelab.dto.pedido.PedidoDTO;
import br.notelab.dto.pedido.StatusPatchDTO;
import br.notelab.service.pedido.PedidoService;
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

@Path("/pedidos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoResource {

    private static final Logger LOG = Logger.getLogger(PedidoResource.class.getName());
    
    @Inject
    PedidoService pedidoService;

    @GET
    @RolesAllowed({"Funcionario"})
    public Response findAll(){
        LOG.info("Buscando todos os pedidos");

        return Response.ok(pedidoService.findAll()).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        LOG.infof("Buscando pedido com id %d", id);

        return Response.ok(pedidoService.findById(id)).build();
    }

    /*
    @PUT
    @RolesAllowed({"Cliente"})
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, PedidoDTO dto){
        LOG.infof("Atualizando pedido com id %d", id);

        pedidoService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @RolesAllowed({"Cliente"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        LOG.infof("Excluindo pedido com id %d", id);

        pedidoService.delete(id);
        return Response.status(204).build();
    }
    
    */
    @PATCH
    @RolesAllowed({"Funcionario"})
    @Path("/status-pedido/{id}")
    public Response updateStatusPedido(@PathParam("id") Long idPedido, StatusPatchDTO status){
        LOG.infof("Atualizando status do pedido com id %d para status %d", idPedido, status.idStatus());

        pedidoService.updateStatusPedido(idPedido, status.idStatus());
        return Response.status(Status.NO_CONTENT).build();
    }

    @POST
    @RolesAllowed({"Cliente"})
    @Path("/{id}/pagamento/info/pix")
    public Response gerarInformacoesPix(@PathParam("id") Long id){
        LOG.infof("Gerando informações de pagamento PIX para pedido com id %d", id);

        return Response.status(201).entity(pedidoService.gerarInformacoesPix(id)).build();
    }

    @POST
    @RolesAllowed({"Cliente"})
    @Path("/{id}/pagamento/info/boleto")
    public Response gerarInformacoesBoleto(@PathParam("id") Long id){
        LOG.infof("Gerando informações de pagamento boleto para pedido com id %d", id);

        return Response.status(201).entity(pedidoService.gerarInformacoesBoleto(id)).build();
    }

    @PATCH
    @RolesAllowed({"Cliente"})
    @Path("/{id}/pagamento/pix/{id-pix}")
    public Response registrarPagamentoPix(@PathParam("id") Long idPedido, @PathParam("id-pix") Long idPix){
        LOG.infof("Registrando pagamento PIX para pedido com id %d e id PIX %d", idPedido, idPix);

        pedidoService.registrarPagamentoPix(idPedido, idPix);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed({"Cliente"})
    @Path("/{id}/pagamento/boleto/{id-boleto}")
    public Response registrarPagamentoBoleto(@PathParam("id") Long idPedido, @PathParam("id-boleto") Long idBoleto){
        LOG.infof("Registrando pagamento boleto para pedido com id %d e id boleto %d", idPedido, idBoleto);

        pedidoService.registrarPagamentoBoleto(idPedido, idBoleto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed({"Cliente"})
    @Path("/{id}/pagamento/cartao")
    public Response registrarPagamentoCartao(@PathParam("id") Long id, CartaoDTO cartao){ 
        LOG.infof("Registrando pagamento com cartão para pedido com id %d", id);

        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @RolesAllowed({"Cliente", "Funcionario"})
    @Path("/search/cliente/{id}")
    public Response findByCliente(@PathParam("id") Long id){
        LOG.infof("Buscando pedidos pelo cliente com id %d", id);

        return Response.ok(pedidoService.findByCliente(id)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/item/{id}")
    public Response findByItem(@PathParam("id") Long id){
        LOG.infof("Buscando pedidos pelo notebook com id %d", id);

        return Response.ok(pedidoService.findByItem(id)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/status/{id}")
    public Response findByStatus(@PathParam("id") Integer id){
        LOG.infof("Buscando pedidos pelo status com id %d", id);

        return Response.ok(pedidoService.findByStatus(id)).build();
    }

    /*
    @GET
    @Path("/search/total")
    // @RolesAllowed("Funcionario")
    public Response findByTotal(@QueryParam("valorInicial") Double valorInicial, @QueryParam("valorFinal") Double valorFinal){
        return Response.ok(pedidoService.findBetweenTotais(valorInicial, valorFinal)).build();
    }

    @GET
    @Path("/search/data")
    // @RolesAllowed("Funcionario")
    public Response findByData(@QueryParam("dataInicial") LocalDate dataInicial, @QueryParam("dataFinal") LocalDate dataFinal){
        return Response.ok(pedidoService.findBetweenDatas(dataFinal, dataFinal)).build();
    }
    */
}