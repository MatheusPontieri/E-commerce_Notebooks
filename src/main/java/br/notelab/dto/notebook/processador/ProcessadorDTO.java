package br.notelab.dto.notebook.processador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProcessadorDTO(
    @NotBlank(message = "O modelo não pode ser nulo ou vazio")
    @Size(min = 4, max = 30, message = "O modelo deve ter entre 3 e 30 caracteres")
    String modelo,

    @NotBlank(message = "A velocidade não pode ser nula ou vazia")
    @Size(min = 3, max = 10, message = "A velocidade deve ter entre 3 e 10 caracteres")
    String velocidade,

    @NotBlank(message = "A memória cache não pode ser nula ou vazia")
    @Size(min = 3, max = 10, message = "A memória cache deve ter entre 3 e 10 caracteres")
    String memoriaCache
){

}
