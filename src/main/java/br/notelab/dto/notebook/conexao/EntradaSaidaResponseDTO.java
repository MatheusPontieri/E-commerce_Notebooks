package br.notelab.dto.notebook.conexao;

import br.notelab.model.notebook.conexao.EntradaSaida;

public record EntradaSaidaResponseDTO(
    Long id,
    String nome
) {
    public static EntradaSaidaResponseDTO valueOf(EntradaSaida e){
        return new EntradaSaidaResponseDTO(e.getId(), e.getNome());
    }
}
