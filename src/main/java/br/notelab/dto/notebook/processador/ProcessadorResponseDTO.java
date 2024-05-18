package br.notelab.dto.notebook.processador;

import br.notelab.model.notebook.Processador;

public record ProcessadorResponseDTO(
    Long id,
    String modelo,
    String velocidade,
    String memoriaCache
){
    public static ProcessadorResponseDTO valueOf(Processador p){
        return new ProcessadorResponseDTO(p.getId(), p.getModelo(), p.getVelocidade(), p.getMemoriaCache());
    }

}
