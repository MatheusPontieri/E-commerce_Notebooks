package br.notelab.service;

import java.io.File;

public interface FileService {
    void upload(Long id, String nomeImagem, byte[] imagem);
    File download(String nomeImagem);
}