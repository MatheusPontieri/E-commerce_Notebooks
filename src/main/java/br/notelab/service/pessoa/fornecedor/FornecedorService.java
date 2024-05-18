package br.notelab.service.pessoa.fornecedor;

import java.util.List;

import br.notelab.dto.pessoa.fornecedor.FornecedorDTO;
import br.notelab.dto.pessoa.fornecedor.FornecedorResponseDTO;
import jakarta.validation.Valid;

public interface FornecedorService {
    FornecedorResponseDTO create(@Valid FornecedorDTO dto);
    void update(Long id, @Valid FornecedorDTO dto);
    void delete(Long id);
    FornecedorResponseDTO findById(Long id);
    List<FornecedorResponseDTO> findAll();
    List<FornecedorResponseDTO> findByNome(String modelo);
}
