package br.notelab.dto.endereco.cidade;

import br.notelab.dto.endereco.estado.EstadoResponseDTO;
import br.notelab.model.endereco.Cidade;

public record CidadeResponseDTO(
    Long id,
    String nome,
    EstadoResponseDTO estado
) {
    public static CidadeResponseDTO valueOf(Cidade c){
        return new CidadeResponseDTO(c.getId(), c.getNome(), EstadoResponseDTO.valueOf(c.getEstado()));
    }
}
