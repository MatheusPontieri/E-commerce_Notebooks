package br.notelab.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120, nullable = false)
    private String descricao;

    @Column(length = 8, nullable = false)
    private Double preco;

    @Column(length = 15, nullable = false)
    private String modelo;

    @Column(length = 15, nullable = true)
    private String linha;

    @Column(length = 15, nullable = true)
    private String serie;

    @Column
    private boolean isGamer;

    @ManyToOne
    @JoinColumn(name = "id_processador")
    private Processador processador;

    @ManyToOne
    @JoinColumn(name = "id_placa_video")
    private PlacaVideo placaVideo;

    @OneToOne
    @JoinColumn(name = "id_especificacao")
    private Especificacao especificacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public boolean isGamer() {
        return isGamer;
    }

    public void setGamer(boolean isGamer) {
        this.isGamer = isGamer;
    }

    public Processador getProcessador() {
        return processador;
    }

    public void setProcessador(Processador processador) {
        this.processador = processador;
    }

    public PlacaVideo getPlacaVideo() {
        return placaVideo;
    }

    public void setPlacaVideo(PlacaVideo placaVideo) {
        this.placaVideo = placaVideo;
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao especificacao) {
        this.especificacao = especificacao;
    }
}
