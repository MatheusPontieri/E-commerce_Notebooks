package br.notelab.dto.notebook.especificacao;

import br.notelab.model.notebook.Especificacao;

public record EspecificacaoResponseDTO(
    String altura,
    String largura,
    String profundidade,
    String peso
) {
   public static EspecificacaoResponseDTO valueOf(Especificacao e){
        return new EspecificacaoResponseDTO(e.getAltura(), e.getLargura(), e.getProfundidade(), e.getPeso());
   } 
}
