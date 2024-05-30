package br.notelab.dto.pedido.item_pedido;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPedidoDTO(
    @Positive(message = "O id do cupom não pode ser negativo ou 0")
    Long idCupom,

    @NotNull(message = "A quantidade não pode ser nula")
    @Positive(message = "A quantidade não pode ser negativa ou 0")
    Integer quantidade,

    @NotNull(message = "O id do notebook não pode ser nulo")
    @Positive(message = "O id do notebook não pode ser negativo ou 0")
    Long idNotebook
) {

}