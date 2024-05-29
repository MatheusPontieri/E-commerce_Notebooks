package br.notelab.repository;

import java.time.LocalDateTime;
import java.util.List;

import br.notelab.model.pedido.Cupom;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CupomRepository implements PanacheRepository<Cupom> {
    public List<Cupom> findByCodigo(String codigo){
        return find("UPPER(codigo) LIKE ?1", "%" + codigo.toUpperCase() + "%").list();
    }

    public List<Cupom> findByFornecedor(Long idFornecedor){
        return find("fornecedor.id = ?1", idFornecedor).list();
    }
}