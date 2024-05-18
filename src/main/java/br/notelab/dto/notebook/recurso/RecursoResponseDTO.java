package br.notelab.dto.notebook.recurso;

import br.notelab.model.notebook.Recurso;

public record RecursoResponseDTO(
    Long id,
    String nome
){
    public static RecursoResponseDTO valueOf(Recurso recurso){
        return new RecursoResponseDTO(recurso.getId(), recurso.getNome());
    }
    
}
