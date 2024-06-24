package br.notelab.service.file;

import java.io.File;

public interface FileService {
    void upload(Long id, String nomeImagem, byte[] imagem);
    File download(String nomeImagem);
}