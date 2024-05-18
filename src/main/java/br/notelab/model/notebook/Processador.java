package br.notelab.model.notebook;

import br.notelab.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Processador extends DefaultEntity {
    @Column(nullable = false, unique = true)
    private String modelo;

    @Column(nullable = false)
    private String velocidade;

    @Column(nullable = false)
    private String memoriaCache; 

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(String velocidade) {
        this.velocidade = velocidade;
    }

    public String getMemoriaCache() {
        return memoriaCache;
    }

    public void setMemoriaCache(String memoriaCache) {
        this.memoriaCache = memoriaCache;
    }   
}