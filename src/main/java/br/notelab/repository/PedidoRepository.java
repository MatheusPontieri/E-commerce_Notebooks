package br.notelab.repository;

import java.time.LocalDateTime;
import java.util.List;

import br.notelab.model.pedido.Pedido;
import br.notelab.model.pedido.Status;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido>{
    public List<Pedido> findByCliente(Long idCliente){
        return find("WHERE cliente.id = ?1", idCliente).list();
    }

    public List<Pedido> findByItem(Long idNotebook){
        return find("JOIN listaItem l WHERE l.notebook.id = ?1 ", idNotebook).list();
    }

    public List<Pedido> findByStatus(Integer idStatus){
        return find("JOIN listaStatus l WHERE l.status = ?1", Status.valueOf(idStatus)).list();
    }

    public List<Pedido> findByTotalAcimaMinimo(Double total){
        return find("WHERE total >= ?1", total).list();
    }

    public List<Pedido> findByTotalAbaixoMaximo(Double total){
        return find("WHERE total <= ?1", total).list();
    }

    public List<Pedido> findByDataMinima(LocalDateTime data){
        return find("WHERE data >= ?1", data).list();
    }

    public List<Pedido> findByDataMaxima(LocalDateTime data){
        return find("WHERE data <= ?1", data).list();
    }
}