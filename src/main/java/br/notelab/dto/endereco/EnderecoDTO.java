package br.notelab.dto.endereco;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(
    @Pattern(regexp = "(\\d{5}-\\d{3})", message = "Formato inválido do cep")
    String cep,

    @NotBlank(message = "O logradouro não pode ser nulo ou vazio")
    @Size(min = 5, max = 60, message = "O logradouro deve ter entre 1 e 30 caracteres")
    String logradouro,

    @NotBlank(message = "O logradouro não pode ser nulo ou vazio")
    @Size(min = 5, max = 60, message = "O logradouro deve ter entre 1 e 30 caracteres")
    String bairro,

    @NotNull(message = "O número não pode ser nulo. Digite 0 se não houver")
    @Min(value = 0, message = "O número mínimo é 0")
    int numero,

    @Size(max = 100, message = "O complemento não pode ser maior que 100 caracteres")
    String complemento,

    @NotNull(message = "A cidade não pode ser nula")
    @Positive(message = "O id da cidade não pode ser negativo ou 0")
    Long idCidade
) {
}