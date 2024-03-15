package br.notelab.service;

import java.util.List;

import br.notelab.dto.notebook.MemoriaRamDTO;
import br.notelab.dto.notebook.MemoriaRamResponseDTO;

import jakarta.validation.Valid;

public interface MemoriaRamService {
    MemoriaRamResponseDTO create(@Valid MemoriaRamDTO dto);
    void update(Long id, MemoriaRamDTO dto);
    void delete(Long id);
    MemoriaRamResponseDTO findById(Long id);
    List<MemoriaRamResponseDTO> findAll();
    List<MemoriaRamResponseDTO> findByCapacidade(String capacidade);
}
