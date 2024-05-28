package br.notelab.dto.pedido.item_pedido;

public record ItemPedidoDTO(
    Long idCupom,
    Integer quantidade,
    Long idNotebook
) {

}