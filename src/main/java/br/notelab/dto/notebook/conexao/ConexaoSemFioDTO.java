package br.notelab.dto.notebook.conexao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ConexaoSemFioDTO(
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 1, max = 60, message = "O nome deve ter entre 1 e 60 caracteres")
    String nome
) {
    
}
