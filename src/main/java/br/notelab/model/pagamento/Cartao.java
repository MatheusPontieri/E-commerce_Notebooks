package br.notelab.model.pagamento;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Cartao extends Pagamento {

    @Column(name = "titular_cartao", nullable = false)
    private String titularCartao;

    @Column(name = "cpf_cartao", nullable = false)
    private String cpfCartao;

    @Column(nullable = false)
    private String numero;

    @Column(name = "data_validade", nullable = false)
    private LocalDate dataValidade;

    @Column(name = "codigo_seguranca", nullable = false)
    private Integer codigoSeguranca;

    // private TipoCartao tipo; Débito ou Crédito

    public String getTitularCartao() {
        return titularCartao;
    }

    public void setTitularCartao(String titularCartao) {
        this.titularCartao = titularCartao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Integer getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(Integer codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }
}