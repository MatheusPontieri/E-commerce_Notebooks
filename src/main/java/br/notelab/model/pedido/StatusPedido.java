package br.notelab.model.pedido;

import java.time.LocalDateTime;

import br.notelab.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class StatusPedido extends DefaultEntity {
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Column(nullable = false)
    private Status status;

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}