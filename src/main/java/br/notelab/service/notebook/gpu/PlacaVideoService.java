package br.notelab.service.notebook.gpu;

import java.util.List;

import br.notelab.dto.notebook.gpu.PlacaVideoDTO;
import br.notelab.dto.notebook.gpu.PlacaVideoResponseDTO;
import jakarta.validation.Valid;

public interface PlacaVideoService {
    PlacaVideoResponseDTO create(@Valid PlacaVideoDTO dto);
    void update(Long id, @Valid PlacaVideoDTO dto);
    void delete(Long id);
    PlacaVideoResponseDTO findById(Long id);
    List<PlacaVideoResponseDTO> findAll();
    List<PlacaVideoResponseDTO> findByModelo(String modelo);
    List<PlacaVideoResponseDTO> findByMemoriaVideo(String memoria);
}
