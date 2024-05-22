package br.notelab.dto.pedido;

import java.time.LocalDateTime;

import br.notelab.model.pedido.Status;
import br.notelab.model.pedido.StatusPedido;

public record StatusPedidoResponseDTO(
    LocalDateTime dataAtualizacao,
    Status status
) {
    public static StatusPedidoResponseDTO valueOf(StatusPedido s){
        return new StatusPedidoResponseDTO(
            s.getDataAtualizacao(),
            s.getStatus()
        );
    }
}