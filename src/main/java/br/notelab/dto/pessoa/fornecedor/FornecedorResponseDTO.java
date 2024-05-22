package br.notelab.dto.pessoa.fornecedor;

import java.util.List;

import br.notelab.dto.pessoa.telefone.TelefoneResponseDTO;
import br.notelab.model.pessoa.Fornecedor;

public record FornecedorResponseDTO(
    Long id,
    String nome,
    String email,
    String cnpj,
    List<TelefoneResponseDTO> telefones
) {
    public static FornecedorResponseDTO valueOf(Fornecedor f){
        return new FornecedorResponseDTO(
            f.getId(),
            f.getNome(),
            f.getEmail(), 
            f.getCnpj(), 
            f.getListaTelefone().stream().map(TelefoneResponseDTO::valueOf).toList()
        );
    }
    
}
