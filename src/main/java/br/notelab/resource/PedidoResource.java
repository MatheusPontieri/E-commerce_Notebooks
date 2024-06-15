package br.notelab.resource;

import java.time.LocalDate;

import br.notelab.dto.pagamento.CartaoDTO;
import br.notelab.dto.pedido.PedidoDTO;
import br.notelab.service.pedido.PedidoService;
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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/pedidos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoResource {
    
    @Inject
    public PedidoService pedidoService;

    @GET
    public Response findAll(){
        return Response.ok(pedidoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(pedidoService.findById(id)).build();
    }

    @POST
    public Response create(PedidoDTO dto){
        return Response
            .status(201)
            .entity(pedidoService.create(dto))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response create(@PathParam("id") Long id, PedidoDTO dto){
        pedidoService.update(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        pedidoService.delete(id);
        return Response.status(204).build();
    }
    
    @PATCH
    @Path("/status-pedido/{id}")
    public Response updateStatusPedido(@PathParam("id") Long idPedido, Integer idStatus){
        pedidoService.updateStatusPedido(idPedido, idStatus);

        return Response.status(Status.NO_CONTENT).build();
    }

    @POST
    @Path("/{id}/pagamento/info/pix")
    public Response gerarInformacoesPix(@PathParam("id") Long id){
        return Response.status(201).entity(pedidoService.gerarInformacoesPix(id)).build();
    }

    @POST
    @Path("/{id}/pagamento/info/boleto")
    public Response gerarInformacoesBoleto(@PathParam("id") Long id){
        return Response.status(201).entity(pedidoService.gerarInformacoesBoleto(id)).build();
    }

    @PATCH
    @Path("/{id}/pagamento/pix/{id-pix}")
    public Response registrarPagamentoPix(@PathParam("id") Long idPedido, @PathParam("id-pix") Long idPix){

        pedidoService.registrarPagamentoPix(idPedido, idPix);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/{id}/pagamento/boleto/{id-boleto}")
    public Response registrarPagamentoBoleto(@PathParam("id") Long idPedido, @PathParam("id-boleto") Long idBoleto){

        pedidoService.registrarPagamentoBoleto(idPedido, idBoleto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/{id}/pagamento/cartao")
    public Response registrarPagamentoCartao(@PathParam("id") Long id, CartaoDTO cartao){ 

        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/search/cliente/{id}")
    public Response findByCliente(@PathParam("id") Long id){
        return Response.ok(pedidoService.findByCliente(id)).build();
    }

    @GET
    @Path("/search/item/{id}")
    public Response findByItem(@PathParam("id") Long id){
        return Response.ok(pedidoService.findByItem(id)).build();
    }

    @GET
    @Path("/search/status/{id}")
    public Response findByStatus(@PathParam("id") Integer id){
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