package br.notelab.model.pagamento;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Pix extends Pagamento {
    
    @Column(nullable = false)
    private String chaveRecebedor;

    @Column(name = "data_expiracao", nullable = false)
    private LocalDateTime dataExpiracao;

    public String getChaveRecebedor() {
        return chaveRecebedor;
    }

    public void setChaveRecebedor(String chaveRecebedor) {
        this.chaveRecebedor = chaveRecebedor;
    }

    public LocalDateTime getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDateTime dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }
}