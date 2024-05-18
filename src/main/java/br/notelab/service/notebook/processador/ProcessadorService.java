package br.notelab.service.notebook.processador;

import java.util.List;

import br.notelab.dto.notebook.processador.ProcessadorDTO;
import br.notelab.dto.notebook.processador.ProcessadorResponseDTO;
import jakarta.validation.Valid;

public interface ProcessadorService {
    ProcessadorResponseDTO create(@Valid ProcessadorDTO dto);
    void update(Long id, @Valid ProcessadorDTO dto);
    void delete(Long id);
    ProcessadorResponseDTO findById(Long id);
    List<ProcessadorResponseDTO> findAll();
    List<ProcessadorResponseDTO> findByModelo(String modelo);
}
