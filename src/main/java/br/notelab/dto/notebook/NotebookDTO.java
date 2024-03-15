package br.notelab.dto.notebook;

public record NotebookDTO(
    String descricao,
    Double preco,
    String modelo,
    Boolean isGamer,
    Long idProcessador,
    Long idPlacaVideo,
    Long idMemoriaRam){
        
}