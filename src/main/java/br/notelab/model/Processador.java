package br.notelab.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Processador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String modelo;

    @Column(length = 15, nullable = false)
    private String geracao;

    @Column(length = 15, nullable = false)
    private String velocidade;

    @Column(length = 3, nullable = false)
    private int nucleos;

    @Column(length = 3, nullable = false)
    private int threads;

    @Column(length = 15, nullable = false)
    private String memoriaCache;

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

    public String getGeracao() {
        return geracao;
    }

    public void setGeracao(String geracao) {
        this.geracao = geracao;
    }

    public String getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(String velocidade) {
        this.velocidade = velocidade;
    }

    public int getNucleos() {
        return nucleos;
    }

    public void setNucleos(int nucleos) {
        this.nucleos = nucleos;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public String getMemoriaCache() {
        return memoriaCache;
    }

    public void setMemoriaCache(String memoriaCache) {
        this.memoriaCache = memoriaCache;
    }
}
