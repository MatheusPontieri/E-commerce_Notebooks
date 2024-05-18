package br.notelab.service.endereco.cidade;

import java.util.List;

import br.notelab.dto.endereco.cidade.CidadeDTO;
import br.notelab.dto.endereco.cidade.CidadeResponseDTO;
import jakarta.validation.Valid;

public interface CidadeService {
    CidadeResponseDTO create(@Valid CidadeDTO dto);
    void update(Long id, @Valid CidadeDTO dto);
    void delete(Long id);
    CidadeResponseDTO findById(Long id);
    List<CidadeResponseDTO> findAll();
    List<CidadeResponseDTO> findByNome(String nome);
    List<CidadeResponseDTO> findByEstado(String nomeEstado);
}
