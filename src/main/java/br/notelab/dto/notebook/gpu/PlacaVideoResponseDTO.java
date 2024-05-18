package br.notelab.dto.notebook.gpu;

import br.notelab.model.notebook.gpu.PlacaVideo;

public record PlacaVideoResponseDTO(
    Long id,
    String modelo,
    String memoriaVideo
) {
    public static PlacaVideoResponseDTO valueOf(PlacaVideo placa){
        return new PlacaVideoResponseDTO(placa.getId(), placa.getModelo(), placa.getMemoriaVideo());
    }
    
}
