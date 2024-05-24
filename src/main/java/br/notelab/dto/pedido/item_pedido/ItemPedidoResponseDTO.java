package br.notelab.dto.pedido.item_pedido;

import br.notelab.model.pedido.ItemPedido;

public record ItemPedidoResponseDTO(
    Long id,
    String nome,
    Double desconto,
    Integer quantidade
) {
    public static ItemPedidoResponseDTO valueOf(ItemPedido i) {
        return new ItemPedidoResponseDTO(
            i.getId(), 
            i.getNotebook().getDescricao(),    
            i.getDesconto(),
            i.getQuantidade()
        );
    }
}