package br.notelab.service.file;

import java.io.File;

public interface FileService {
    void upload(Long id, String fileName, byte[] file);
    File download(String fileName);
}