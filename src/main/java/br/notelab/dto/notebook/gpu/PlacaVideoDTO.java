package br.notelab.dto.notebook.gpu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PlacaVideoDTO(
    @NotBlank(message = "O modelo não pode ser nulo ou vazio")
    @Size(min = 5, max = 30, message = "O modelo deve ter entre 5 e 30 caracteres")
    String modelo,

    @NotBlank(message = "A memória de vídeo não pode ser nula ou vazia")
    @Size(min = 3, max = 10, message = "A memória de vídeo deve ter entre 3 e 10 caracteres")
    String memoriaVideo
) {
    
}
