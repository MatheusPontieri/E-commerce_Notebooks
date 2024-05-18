package br.notelab.dto.endereco.estado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoDTO(
    @NotBlank(message = "O nome não pode ser vazio ou nulo")
    @Size(min = 4, max = 20, message = "O nome deve ter entre 4 e 20 caracteres")
    String nome,
    
    @NotBlank(message = "A sigla não pode ser vazia ou nula")
    @Size(min = 2, max = 2, message = "A sigla deve ter 2 caracteres")
    String sigla
) {
    
}
