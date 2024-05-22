package br.notelab.dto.pedido.item_pedido;

public record ItemPedidoDTO(
    Double preco,
    Double desconto,
    Integer quantidade,
    Long idNotebook
) {

}