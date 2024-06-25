package br.notelab.dto.file;

import br.notelab.model.file.FileInfo;

public record FileInfoResponseDTO(
    String fileName   
) {
    public static FileInfoResponseDTO valueOf(FileInfo fileInfo){
        return new FileInfoResponseDTO(fileInfo.getFileName());
    }
}