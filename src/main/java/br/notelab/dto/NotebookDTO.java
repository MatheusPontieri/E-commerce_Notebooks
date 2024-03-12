package br.notelab.dto;

public record NotebookDTO(
    String descricao,
    Double preco,
    String modelo,
    String linha, 
    String serie,
    boolean isGamer,
    Long idProcessador,
    Long idPlacaVideo,
    Long idEspecificacao
) {

}