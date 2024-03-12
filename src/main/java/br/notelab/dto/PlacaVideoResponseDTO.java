package br.notelab.dto;

import br.notelab.model.PlacaVideo;

public record PlacaVideoResponseDTO(
    Long id,
    String modelo,
    String tamanhoMemoria
) {
    public static PlacaVideoResponseDTO valueOf(PlacaVideo placa){
        return new PlacaVideoResponseDTO(placa.getId(), placa.getModelo(), placa.getTamanhoMemoria());        
    }
}