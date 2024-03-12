package br.notelab.dto;

import br.notelab.model.Processador;

public record ProcessadorResponseDTO(
    Long id,
    String modelo,
    String geracao,
    String velocidade,
    int nucleos,
    int threads,
    String memoriaCache
) {
    public static ProcessadorResponseDTO valueof(Processador p){
        return new ProcessadorResponseDTO(p.getId(), p.getModelo(), p.getGeracao(), p.getVelocidade(),
        p.getNucleos(), p.getThreads(), p.getMemoriaCache());
    }
}
