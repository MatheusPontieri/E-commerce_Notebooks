package br.notelab.dto.pedido;

import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import br.notelab.dto.pedido.item_pedido.ItemPedidoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PedidoDTO(
    @NotNull(message = "O id do cliente não pode ser nulo")
    @Positive(message = "O id do cliente não pode ser negativo ou 0")
    Long idCliente,

    @NotNull(message = "A lista de itens não pode ser nula")
    @Valid
    @UniqueElements(message = "A lista de itens não deve conter itens repetidos")
    List<ItemPedidoDTO> itens
){
}