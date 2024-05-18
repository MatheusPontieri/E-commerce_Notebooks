package br.notelab.service.notebook.memoria;

import java.util.List;

import br.notelab.dto.notebook.memoria.ArmazenamentoDTO;
import br.notelab.dto.notebook.memoria.ArmazenamentoResponseDTO;
import jakarta.validation.Valid;

public interface ArmazenamentoService {
    ArmazenamentoResponseDTO create(@Valid ArmazenamentoDTO dto);
    void update(Long id, @Valid ArmazenamentoDTO dto);
    void delete(Long id);
    ArmazenamentoResponseDTO findById(Long id);
    List<ArmazenamentoResponseDTO> findAll();
    List<ArmazenamentoResponseDTO> findByNome(String modelo);
    List<ArmazenamentoResponseDTO> findByCapacidade(String capacidade);
}
