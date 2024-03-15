package br.notelab.dto.notebook;

import br.notelab.model.notebook.Processador;

public record ProcessadorResponseDTO(
    Long id,
    String modelo,
    String geracao,
    String velocidade,
    int nucleos,
    int threads,
    String memoriaCache
){
    public static ProcessadorResponseDTO valueOf(Processador p){
        return new ProcessadorResponseDTO(
            p.getId(),
            p.getModelo(),
            p.getGeracao(),
            p.getVelocidade(),
            p.getNucleos(),
            p.getThreads(),
            p.getMemoriaCache()
        );
    }
}
