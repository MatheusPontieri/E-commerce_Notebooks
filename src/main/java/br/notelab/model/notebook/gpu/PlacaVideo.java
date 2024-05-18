package br.notelab.model.notebook.gpu;

import br.notelab.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PlacaVideo extends DefaultEntity {
    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String memoriaVideo;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMemoriaVideo() {
        return memoriaVideo;
    }

    public void setMemoriaVideo(String memoriaVideo) {
        this.memoriaVideo = memoriaVideo;
    }
}
