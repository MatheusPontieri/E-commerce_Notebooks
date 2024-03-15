package br.notelab.service;

import java.util.List;

import br.notelab.dto.notebook.NotebookDTO;
import br.notelab.dto.notebook.NotebookResponseDTO;
import jakarta.validation.Valid;

public interface NotebookService {
    NotebookResponseDTO create(@Valid NotebookDTO dto);
    void update(Long id, NotebookDTO dto);
    void delete(Long id);
    NotebookResponseDTO findById(Long id);
    List<NotebookResponseDTO> findAll();
    List<NotebookResponseDTO> findByDescricao(String descricao);
}
