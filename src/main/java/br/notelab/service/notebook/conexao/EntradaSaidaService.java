package br.notelab.service.notebook.conexao;

import java.util.List;

import br.notelab.dto.notebook.conexao.EntradaSaidaDTO;
import br.notelab.dto.notebook.conexao.EntradaSaidaResponseDTO;
import jakarta.validation.Valid;

public interface EntradaSaidaService {
    EntradaSaidaResponseDTO create(@Valid EntradaSaidaDTO dto);
    void update(Long id, @Valid EntradaSaidaDTO dto);
    void delete(Long id);
    EntradaSaidaResponseDTO findById(Long id);
    List<EntradaSaidaResponseDTO> findAll();
    List<EntradaSaidaResponseDTO> findByNome(String nome);
}
