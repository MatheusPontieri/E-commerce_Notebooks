package br.notelab.dto.pedido.cupom;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CupomDTO(
    @NotBlank(message = "O código não pode ser nulo ou vazio")
    @Size(min = 3, max = 20, message = "O código deve ter entre 3 e 20 caracteres")
    String codigo,

    @NotNull(message = "O percentual de desconto não pode ser nulo")
    @Digits(integer = 1, fraction = 2, message = "O percentual deve ter 1 algarismo como inteiro e no máximo 2 como decimal")
    Float percentualDesconto,

    @Future(message = "A data de validade deve estar no futuro")
    LocalDateTime validade,

    @NotNull(message = "O id da marca não pode ser nulo")
    @Positive(message = "O id do fornecedor não pode ser negativo ou 0")
    Long idFornecedor
) {
    
}