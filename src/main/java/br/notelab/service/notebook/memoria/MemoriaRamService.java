package br.notelab.service.notebook.memoria;

import java.util.List;

import br.notelab.dto.notebook.memoria.MemoriaRamDTO;
import br.notelab.dto.notebook.memoria.MemoriaRamResponseDTO;
import jakarta.validation.Valid;

public interface MemoriaRamService {
    MemoriaRamResponseDTO create(@Valid MemoriaRamDTO dto);
    void update(Long id, @Valid MemoriaRamDTO dto);
    void delete(Long id);
    MemoriaRamResponseDTO findById(Long id);
    List<MemoriaRamResponseDTO> findAll();
    List<MemoriaRamResponseDTO> findByCapacidade(String capacidade);
}
