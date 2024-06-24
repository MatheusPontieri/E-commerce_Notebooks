package br.notelab.dto.file;

import br.notelab.model.imagem.Imagem;

public record ImagemResponseDTO(
    String nomeImagem   
) {
    public static ImagemResponseDTO valueOf(Imagem imagem){
        return new ImagemResponseDTO(imagem.getNomeImagem());
    }
}