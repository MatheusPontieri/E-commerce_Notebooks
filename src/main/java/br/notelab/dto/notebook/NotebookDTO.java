package br.notelab.dto.notebook;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record NotebookDTO(
    @NotBlank(message = "Descrição não pode ser nula ou vazia")
    String descricao,

    @NotNull
    Double preco,

    @NotBlank(message = "Modelo não pode ser nulo ou vazio")
    String modelo,

    @NotNull
    Boolean isGamer,

    @NotNull
    Long idProcessador,

    Long idPlacaVideo,

    @NotNull
    Long idMemoriaRam
){
}