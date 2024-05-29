package br.notelab.service.pedido.cupom;

import java.util.List;

import br.notelab.dto.pedido.cupom.CupomDTO;
import br.notelab.dto.pedido.cupom.CupomResponseDTO;
import jakarta.validation.Valid;

public interface CupomService {
    CupomResponseDTO create(@Valid CupomDTO dto);
    void update(Long id, @Valid CupomDTO dto);
    void delete(Long id);
    CupomResponseDTO findById(Long id);
    List<CupomResponseDTO> findAll();
    List<CupomResponseDTO> findByCodigo(String codigo);
    List<CupomResponseDTO> findByFornecedor(Long idFornecedor);
}