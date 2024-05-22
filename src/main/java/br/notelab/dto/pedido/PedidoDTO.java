package br.notelab.dto.pedido;

import java.util.List;

import br.notelab.dto.pedido.item_pedido.ItemPedidoDTO;

public record PedidoDTO(
    Long idCliente,
    List<ItemPedidoDTO> itens
){
}