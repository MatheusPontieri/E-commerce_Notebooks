package br.notelab.dto.pessoa.fornecedor;

import br.notelab.dto.pessoa.telefone.TelefoneResponseDTO;
import br.notelab.model.pessoa.Fornecedor;

public record FornecedorResponseDTO(
    Long id,
    String nome,
    String email,
    String cnpj,
    TelefoneResponseDTO telefone
) {
    public static FornecedorResponseDTO valueOf(Fornecedor f){
        return new FornecedorResponseDTO(
            f.getId(),
            f.getNome(),
            f.getEmail(), 
            f.getCnpj(), 
            TelefoneResponseDTO.valueOf(f.getTelefone())
        );
    }
    
}
