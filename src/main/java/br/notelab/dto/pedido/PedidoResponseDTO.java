package br.notelab.dto.pedido;

import java.time.LocalDateTime;
import java.util.List;

import br.notelab.dto.pedido.item_pedido.ItemPedidoResponseDTO;
import br.notelab.dto.pessoa.cliente.ClienteResponseDTO;
import br.notelab.model.pagamento.Pagamento;
import br.notelab.model.pedido.Pedido;

public record PedidoResponseDTO(
    Long id,
    ClienteResponseDTO cliente,
    Double total,
    List<ItemPedidoResponseDTO> itens,
    List<StatusPedidoResponseDTO> status,
    LocalDateTime prazoPagamento,
    Pagamento pagamento
){
    public static PedidoResponseDTO valueOf(Pedido p){
        return new PedidoResponseDTO(
            p.getId(),
            ClienteResponseDTO.valueOf(p.getCliente()),
            p.getTotal(),
            p.getListaItem().stream().map(ItemPedidoResponseDTO::valueOf).toList(),
            p.getListaStatus().stream().map(StatusPedidoResponseDTO::valueOf).toList(),
            p.getPrazoPagamento(),
            p.getPagamento()
        );
    }
}