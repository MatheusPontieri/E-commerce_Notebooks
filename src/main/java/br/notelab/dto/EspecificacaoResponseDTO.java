package br.notelab.dto;

import br.notelab.model.Especificacao;

public record EspecificacaoResponseDTO(
    Long id, 
    String altura,
    String largura,
    String profundidade,
    String peso
) {
    public static EspecificacaoResponseDTO valueOf(Especificacao e){
        return new EspecificacaoResponseDTO(e.getId(), e.getAltura(), e.getLargura(),
        e.getProfundidade(), e.getPeso());
    }
}
