package br.notelab.service.notebook.conexao;

import java.util.List;

import br.notelab.dto.notebook.conexao.ConexaoSemFioDTO;
import br.notelab.dto.notebook.conexao.ConexaoSemFioResponseDTO;
import jakarta.validation.Valid;

public interface ConexaoSemFioService {
    ConexaoSemFioResponseDTO create(@Valid ConexaoSemFioDTO dto);
    void update(Long id, @Valid ConexaoSemFioDTO dto);
    void delete(Long id);
    ConexaoSemFioResponseDTO findById(Long id);
    List<ConexaoSemFioResponseDTO> findAll();
    List<ConexaoSemFioResponseDTO> findByNome(String nome);
}
