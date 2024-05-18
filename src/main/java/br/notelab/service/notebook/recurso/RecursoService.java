package br.notelab.service.notebook.recurso;

import java.util.List;

import br.notelab.dto.notebook.recurso.RecursoDTO;
import br.notelab.dto.notebook.recurso.RecursoResponseDTO;
import jakarta.validation.Valid;

public interface RecursoService {
    RecursoResponseDTO create(@Valid RecursoDTO dto);
    void update(Long id, @Valid RecursoDTO dto);
    void delete(Long id);
    RecursoResponseDTO findById(Long id);
    List<RecursoResponseDTO> findAll();
    List<RecursoResponseDTO> findByNome(String nome);
}
