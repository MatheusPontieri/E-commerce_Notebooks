package br.notelab.resource;


import java.time.LocalDateTime;

import br.notelab.dto.pedido.PedidoDTO;
import br.notelab.service.pedido.PedidoService;
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

    @GET
    @Path("/search/total-minimo/{total}")
    @RolesAllowed("Funcionario")
    public Response findByTotalMinimo(@PathParam("total") Double total){
        return Response.ok(pedidoService.findByTotalAcimaMinimo(total)).build();
    }

    @GET
    @Path("/search/total-maximo/{total}")
    @RolesAllowed("Funcionario")
    public Response findByTotalMaximo(@PathParam("total") Double total){
        return Response.ok(pedidoService.findByTotalAbaixoMaximo(total)).build();
    }
/*
    @GET
    @Path("/search/data-minima/{data}")
    @RolesAllowed("Funcionario")
    public Response findByDataMinima(@PathParam("data") LocalDateTime data){
        return Response.ok(pedidoService.findByDataMinima(data)).build();
    }

    @GET
    @Path("/search/data-maxima/{data}")
    @RolesAllowed("Funcionario")
    public Response findByDataMaxima(@PathParam("data") LocalDateTime data){
        return Response.ok(pedidoService.findByDataMaxima(data)).build();
    }
*/
}