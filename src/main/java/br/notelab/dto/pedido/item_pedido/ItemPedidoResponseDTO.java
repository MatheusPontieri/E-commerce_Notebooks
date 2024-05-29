package br.notelab.dto.pedido.item_pedido;

import br.notelab.dto.pedido.cupom.CupomResponseDTO;
import br.notelab.model.pedido.ItemPedido;

public record ItemPedidoResponseDTO(
    Long id,
    String nome,
    Integer quantidade,
    CupomResponseDTO cupom
) {
    public static ItemPedidoResponseDTO valueOf(ItemPedido i) {
        return new ItemPedidoResponseDTO(
            i.getId(), 
            i.getNotebook().getDescricao(),    
            i.getQuantidade(),
            CupomResponseDTO.valueOf(i.getCupom())
        );
    }
}