package br.notelab.service;

import java.util.List;

import br.notelab.dto.notebook.ProcessadorDTO;
import br.notelab.dto.notebook.ProcessadorResponseDTO;
import jakarta.validation.Valid;

public interface ProcessadorService {
    ProcessadorResponseDTO create(@Valid ProcessadorDTO dto);
    void update(Long id, ProcessadorDTO dto);
    void delete(Long id);
    ProcessadorResponseDTO findById(Long id);
    List<ProcessadorResponseDTO> findAll();
    List<ProcessadorResponseDTO> findByModelo(String modelo);
    List<ProcessadorResponseDTO> findByGeracao(String geracao);
}
