package br.notelab.dto.notebook.memoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ArmazenamentoDTO(
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 2, max = 30, message = "O nome deve ter entre 2 e 30 caracteres")
    String nome,

    @NotBlank(message = "A capacidade não pode ser nula ou vazia")
    @Size(min = 3, max = 10, message = "A capacidade deve ter entre 3 e 10 caracteres")
    String capacidade
) {
    
}
