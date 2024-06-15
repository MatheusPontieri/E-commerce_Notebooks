package br.notelab.resource;

import org.jboss.logging.Logger;

import br.notelab.dto.pagamento.CartaoDTO;
import br.notelab.dto.pedido.PedidoDTO;
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
    public PedidoService pedidoService;

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
        LOG.infov("Buscando pedido com id {0} ", id);

        return Response.ok(pedidoService.findById(id)).build();
    }

    @POST
    @RolesAllowed({"Cliente"})
    public Response create(PedidoDTO dto){
        LOG.info("Criando novo pedido");

        return Response
            .status(201)
            .entity(pedidoService.create(dto))
            .build();
    }

    @PUT
    @RolesAllowed({"Cliente"})
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, PedidoDTO dto){
        LOG.infov("Atualizando pedido com id {0}", id);

        pedidoService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @RolesAllowed({"Cliente"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        LOG.infov("Excluindo pedido com id {0}", id);

        pedidoService.delete(id);
        return Response.status(204).build();
    }
    
    @PATCH
    @RolesAllowed({"Funcionario"})
    @Path("/status-pedido/{id}")
    public Response updateStatusPedido(@PathParam("id") Long idPedido, Integer idStatus){
        LOG.infov("Atualizando status do pedido com id {0} para status {1}", idPedido, idStatus);

        pedidoService.updateStatusPedido(idPedido, idStatus);

        return Response.status(Status.NO_CONTENT).build();
    }

    @POST
    @RolesAllowed({"Cliente"})
    @Path("/{id}/pagamento/info/pix")
    public Response gerarInformacoesPix(@PathParam("id") Long id){
        LOG.infov("Gerando informações de pagamento PIX para pedido com id {0}", id);

        return Response.status(201).entity(pedidoService.gerarInformacoesPix(id)).build();
    }

    @POST
    @RolesAllowed({"Cliente"})
    @Path("/{id}/pagamento/info/boleto")
    public Response gerarInformacoesBoleto(@PathParam("id") Long id){
        LOG.infov("Gerando informações de pagamento boleto para pedido com id {0}", id);

        return Response.status(201).entity(pedidoService.gerarInformacoesBoleto(id)).build();
    }

    @PATCH
    @RolesAllowed({"Cliente"})
    @Path("/{id}/pagamento/pix/{id-pix}")
    public Response registrarPagamentoPix(@PathParam("id") Long idPedido, @PathParam("id-pix") Long idPix){
        LOG.infov("Registrando pagamento PIX para pedido com id {0} e id PIX {1}", idPedido, idPix);

        pedidoService.registrarPagamentoPix(idPedido, idPix);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed({"Cliente"})
    @Path("/{id}/pagamento/boleto/{id-boleto}")
    public Response registrarPagamentoBoleto(@PathParam("id") Long idPedido, @PathParam("id-boleto") Long idBoleto){
        LOG.infov("Registrando pagamento boleto para pedido com id {0} e id boleto {1}", idPedido, idBoleto);

        pedidoService.registrarPagamentoBoleto(idPedido, idBoleto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed({"Cliente"})
    @Path("/{id}/pagamento/cartao")
    public Response registrarPagamentoCartao(@PathParam("id") Long id, CartaoDTO cartao){ 
        LOG.infov("Registrando pagamento com cartão para pedido com id {0}", id);

        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @RolesAllowed({"Cliente", "Funcionario"})
    @Path("/search/cliente/{id}")
    public Response findByCliente(@PathParam("id") Long id){
        LOG.infov("Buscando pedidos pelo cliente com id {0}", id);

        return Response.ok(pedidoService.findByCliente(id)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/item/{id}")
    public Response findByItem(@PathParam("id") Long id){
        LOG.infov("Buscando pedidos pelo notebook com id {0}", id);

        return Response.ok(pedidoService.findByItem(id)).build();
    }

    @GET
    @RolesAllowed({"Funcionario"})
    @Path("/search/status/{id}")
    public Response findByStatus(@PathParam("id") Integer id){
        LOG.infov("Buscando pedidos pelo status com id {0}", id);

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