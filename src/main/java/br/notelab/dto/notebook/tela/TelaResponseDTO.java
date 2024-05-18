package br.notelab.dto.notebook.tela;

import br.notelab.model.notebook.Tela;

public record TelaResponseDTO(
    String tamanho,
    String resolucao,
    String taxaAtualizacao
) {
   public static TelaResponseDTO valueOf(Tela t){
        return new TelaResponseDTO(t.getTamanho(), t.getResolucao(), t.getTaxaAtualizacao());
   } 
}
