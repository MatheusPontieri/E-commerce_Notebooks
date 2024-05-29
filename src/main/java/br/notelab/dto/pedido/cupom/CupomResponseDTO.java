package br.notelab.dto.pedido.cupom;

import java.time.LocalDateTime;

import br.notelab.dto.pessoa.fornecedor.FornecedorResponseDTO;
import br.notelab.model.pedido.Cupom;

public record CupomResponseDTO(
    Long id,
    String codigo,
    Float percentualDesconto,
    LocalDateTime validade,
    FornecedorResponseDTO fornecedor
) {
    public static CupomResponseDTO valueOf(Cupom c){
        return new CupomResponseDTO(
            c.getId(),
            c.getCodigo(),
            c.getPercentualDesconto(),
            c.getDataValidade(),
            FornecedorResponseDTO.valueOf(c.getFornecedor())
        );
    }   
}