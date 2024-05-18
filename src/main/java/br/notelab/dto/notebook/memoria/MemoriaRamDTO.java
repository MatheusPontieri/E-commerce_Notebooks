package br.notelab.dto.notebook.memoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemoriaRamDTO(
    @NotBlank(message = "A capacidade não pode ser nula ou vazia")
    @Size(min = 3, max = 10, message = "A capacidade deve ter entre 3 e 10 caracteres")
    String capacidade,

    @NotBlank(message = "O limite de expansão não pode ser nulo ou vazio")
    @Size(min = 3, max = 10, message = "O limite de expansão deve ter entre 3 e 10 caracteres")
    String limiteExpansao
) {
    
}
