package br.notelab.model.pedido;

import br.notelab.model.DefaultEntity;
import br.notelab.model.pessoa.Fornecedor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cupom extends DefaultEntity {

    @Column(nullable = false)
    private String codigo;
    
    @Column(nullable = false, name = "percentual_desconto")
    private Float percentualDesconto;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Float getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(Float percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}