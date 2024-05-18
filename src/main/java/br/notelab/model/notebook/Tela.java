package br.notelab.model.notebook;

import br.notelab.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Tela extends DefaultEntity {
    @Column(nullable = false)
    private String tamanho;

    @Column(nullable = false)
    private String resolucao;

    @Column(nullable = false)
    private String taxaAtualizacao;

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }

    public String getTaxaAtualizacao() {
        return taxaAtualizacao;
    }

    public void setTaxaAtualizacao(String taxaAtualizacao) {
        this.taxaAtualizacao = taxaAtualizacao;
    }
}
