package br.notelab.dto.pessoa.telefone;

import br.notelab.model.pessoa.Telefone;

public record TelefoneResponseDTO(
    String codigoArea,
    String numero
) {
    public static TelefoneResponseDTO valueOf(Telefone t){
        return new TelefoneResponseDTO(t.getCodigoArea(), t.getNumero());
    }
    
}
