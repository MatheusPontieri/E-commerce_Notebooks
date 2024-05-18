package br.notelab.dto.notebook.conexao;

import br.notelab.model.notebook.conexao.ConexaoSemFio;

public record ConexaoSemFioResponseDTO(
    Long id,
    String nome
) {
    public static ConexaoSemFioResponseDTO valueOf(ConexaoSemFio conexao){
        return new ConexaoSemFioResponseDTO(conexao.getId(), conexao.getNome());
    }
    
}