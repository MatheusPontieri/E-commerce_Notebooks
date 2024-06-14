package br.notelab.dto.pagamento;

import java.time.LocalDateTime;

import br.notelab.model.pagamento.Boleto;

public record BoletoResponseDTO(
    Long id, 
    Double valor,
    LocalDateTime dataLimite,
    String codigo
){    

    public static BoletoResponseDTO valueOf(Boleto boleto){
        return new BoletoResponseDTO(
            boleto.getId(),
            boleto.getValor(),
            boleto.getDataLimite(),
            boleto.getCodigo()
        );
    }
    
}