package br.notelab.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PlacaVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15, nullable = false)
    private String modelo;

    @Column(length = 15, nullable = false)
    private String tamanhoMemoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTamanhoMemoria() {
        return tamanhoMemoria;
    }

    public void setTamanhoMemoria(String tamanhoMemoria) {
        this.tamanhoMemoria = tamanhoMemoria;
    }
}