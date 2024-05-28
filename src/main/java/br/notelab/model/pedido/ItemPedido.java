package br.notelab.model.pedido;

import br.notelab.model.DefaultEntity;
import br.notelab.model.notebook.Notebook;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido extends DefaultEntity {
    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "id_notebook", nullable = false)
    private Notebook notebook;

    @ManyToOne
    @JoinColumn(name = "id_cupom")
    private Cupom cupom;

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Notebook getNotebook() {
        return notebook;
    }

    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }

    public Cupom getCupom() {
        return cupom;
    }

    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
    }
}