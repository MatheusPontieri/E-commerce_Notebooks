package br.notelab.dto.notebook.memoria;

import br.notelab.model.notebook.memoria.MemoriaRam;

public record MemoriaRamResponseDTO(
    Long id,
    String capacidade,
    String limiteExpansao
) {
    public static MemoriaRamResponseDTO valueOf(MemoriaRam m){
        return new MemoriaRamResponseDTO(m.getId(), m.getCapacidade(), m.getLimiteExpansao());
    }
}