package br.notelab.dto.notebook;

import br.notelab.model.notebook.MemoriaRam;

public record MemoriaRamResponseDTO(
    Long id,
    String capacidade,
    String expansivelAte
){
    public static MemoriaRamResponseDTO valueOf(MemoriaRam memoria){
        return new MemoriaRamResponseDTO(
            memoria.getId(),
            memoria.getCapacidade(),
            memoria.getExpansivelAte()
        );
    }
    
}