package br.notelab.dto.notebook.recurso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RecursoDTO(
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 2, max = 30, message = "O nome deve ter entre 2 e 30 caracteres")
    String nome
){
    
}
