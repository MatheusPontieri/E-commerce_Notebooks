package br.notelab.dto.notebook.memoria;

import br.notelab.model.notebook.memoria.Armazenamento;

public record ArmazenamentoResponseDTO(
    Long id,
    String nome,
    String capacidade
) {
    public static ArmazenamentoResponseDTO valueOf(Armazenamento a){
        return new ArmazenamentoResponseDTO(a.getId(), a.getNome(), a.getCapacidade());
    }
    
}
