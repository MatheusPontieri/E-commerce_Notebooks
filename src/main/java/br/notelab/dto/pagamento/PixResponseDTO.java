package br.notelab.dto.pagamento;

import java.time.LocalDateTime;

import br.notelab.model.pagamento.Pix;

public record PixResponseDTO(
    Long id,
    Double valor,
    String chaveDestinatario,
    String identificador
) {
    
    public static PixResponseDTO valueOf(Pix pix){
        return new PixResponseDTO(
            pix.getId(),
            pix.getValor(),
            pix.getChaveDestinatario(),
            pix.getIdentificador()
        );
    }

}