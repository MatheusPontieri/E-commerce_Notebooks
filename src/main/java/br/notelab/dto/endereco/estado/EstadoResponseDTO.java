package br.notelab.dto.endereco.estado;

import br.notelab.model.endereco.Estado;

public record EstadoResponseDTO(
    Long id,
    String nome,
    String sigla
) {
    public static EstadoResponseDTO valueOf(Estado e){
        return new EstadoResponseDTO(e.getId(), e.getNome(), e.getSigla());
    }
}
