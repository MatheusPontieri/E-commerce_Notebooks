package br.notelab.service;

import java.util.List;

import br.notelab.dto.notebook.PlacaVideoDTO;
import br.notelab.dto.notebook.PlacaVideoResponseDTO;

import jakarta.validation.Valid;

public interface PlacaVideoService {
    PlacaVideoResponseDTO create(@Valid PlacaVideoDTO dto);
    void update(Long id, PlacaVideoDTO dto);
    void delete(Long id);
    PlacaVideoResponseDTO findById(Long id);
    List<PlacaVideoResponseDTO> findAll();
    List<PlacaVideoResponseDTO> findByModelo(String modelo);
}
