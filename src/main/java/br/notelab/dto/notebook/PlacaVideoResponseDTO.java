package br.notelab.dto.notebook;

import br.notelab.model.notebook.PlacaVideo;

public record PlacaVideoResponseDTO(
    Long id,
    String modelo,
    String memoriaVideo
) {
    public static PlacaVideoResponseDTO valueOf(PlacaVideo placa){
        return new PlacaVideoResponseDTO(
            placa.getId(),
            placa.getModelo(),
            placa.getMemoriaVideo());
    }
}
