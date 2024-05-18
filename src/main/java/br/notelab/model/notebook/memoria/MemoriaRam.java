package br.notelab.model.notebook.memoria;

import br.notelab.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class MemoriaRam extends DefaultEntity {
    @Column(nullable = false)
    private String capacidade;

    @Column(nullable = false)
    private String limiteExpansao;

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    public String getLimiteExpansao() {
        return limiteExpansao;
    }

    public void setLimiteExpansao(String limiteExpansao) {
        this.limiteExpansao = limiteExpansao;
    }
}
