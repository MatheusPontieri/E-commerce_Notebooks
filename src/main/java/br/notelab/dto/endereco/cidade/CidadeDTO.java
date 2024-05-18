package br.notelab.dto.endereco.cidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CidadeDTO(    
    @NotBlank(message = "O nome não pode ser vazio ou nulo")
    @Size(min = 2, max = 20, message = "O nome deve ter entre 2 e 20 caracteres")
    String nome,
    
    @NotNull(message = "O estado não pode ser nulo")
    @Positive(message = "O id do estado não pode ser negativo ou 0")
    Long idEstado
) {
    
}
