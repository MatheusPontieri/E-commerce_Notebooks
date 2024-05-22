package br.notelab.model.pedido;

import java.time.LocalDateTime;
import java.util.List;

import br.notelab.model.DefaultEntity;
import br.notelab.model.pessoa.Cliente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido extends DefaultEntity{

    @Column(nullable = false)
    private LocalDateTime data;
    
    @Column(nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_pedido", nullable = false)
    private List<ItemPedido> listaItem;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "status_pedido",
               joinColumns = @JoinColumn(name = "id_pedido"),
               inverseJoinColumns = @JoinColumn(name = "status"))
    private List<StatusPedido> listaStatus;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getListaItem() {
        return listaItem;
    }

    public void setListaItem(List<ItemPedido> listaItem) {
        this.listaItem = listaItem;
    }

    public List<StatusPedido> getListaStatus() {
        return listaStatus;
    }

    public void setListaStatus(List<StatusPedido> listaStatus) {
        this.listaStatus = listaStatus;
    }
}