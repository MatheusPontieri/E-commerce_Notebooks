package br.notelab.dto;

import br.notelab.model.Notebook;

public record NotebookResponseDTO(
    Long id,
    String descricao,
    Double preco,
    String modelo,
    String linha, 
    String serie,
    boolean isGamer,
    ProcessadorResponseDTO processador,
    PlacaVideoResponseDTO placa,
    EspecificacaoResponseDTO especificacao
) {
    public static NotebookResponseDTO valueOf(Notebook note){
        return new NotebookResponseDTO(note.getId(), note.getDescricao(), note.getPreco(), note.getModelo(),
        note.getLinha(), note.getSerie(), note.isGamer(), 
        ProcessadorResponseDTO.valueof(note.getProcessador()),
        PlacaVideoResponseDTO.valueOf(note.getPlacaVideo()),
        EspecificacaoResponseDTO.valueOf(note.getEspecificacao()));
    }  
}