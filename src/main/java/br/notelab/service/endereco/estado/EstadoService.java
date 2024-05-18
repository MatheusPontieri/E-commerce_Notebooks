package br.notelab.service.endereco.estado;

import java.util.List;

import br.notelab.dto.endereco.estado.EstadoDTO;
import br.notelab.dto.endereco.estado.EstadoResponseDTO;
import jakarta.validation.Valid;

public interface EstadoService {
    EstadoResponseDTO create(@Valid EstadoDTO dto);
    void update(Long id, @Valid EstadoDTO dto);
    void delete(Long id);
    EstadoResponseDTO findById(Long id);
    List<EstadoResponseDTO> findAll();
    List<EstadoResponseDTO> findByNome(String nome);
    List<EstadoResponseDTO> findBySigla(String sigla);
}