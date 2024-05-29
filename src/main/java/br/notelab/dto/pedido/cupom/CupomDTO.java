package br.notelab.dto.pedido.cupom;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.UUID;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CupomDTO(

    @NotBlank(message = "O código não pode ser nulo ou vazio")
    String codigo,

    @NotNull(message = "O desconto não poder nulo")
    Float percentualDesconto,

    @Future
    LocalDateTime validade,

    @NotNull(message = "O id da marca não pode ser nulo")
    @Min(value = 1, message = "O id da marca não pode ser menos que 1")
    Long idFornecedor
) {
    
}