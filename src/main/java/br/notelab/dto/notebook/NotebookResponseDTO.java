package br.notelab.dto.notebook;

import br.notelab.model.notebook.Notebook;

public record NotebookResponseDTO(
    Long id,
    String descricao,
    String modelo,
    Double preco,
    Boolean isGamer,
    ProcessadorResponseDTO processador,
    PlacaVideoResponseDTO placaVideo,
    MemoriaRamResponseDTO memoriaRam
) {
    public static NotebookResponseDTO valueOf(Notebook note){
        return new NotebookResponseDTO(
            note.getId(),
            note.getDescricao(),
            note.getModelo(),
            note.getPreco(),
            note.getIsGamer(),
            ProcessadorResponseDTO.valueOf(note.getProcessador()),
            PlacaVideoResponseDTO.valueOf(note.getPlacaVideo()),
            MemoriaRamResponseDTO.valueOf(note.getMemoriaRam())
        );
    }
}
