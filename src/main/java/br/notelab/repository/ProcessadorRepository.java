package br.notelab.repository;

import java.util.List;

import br.notelab.model.Processador;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProcessadorRepository implements PanacheRepository<Processador> {
    public List<Processador> findByModelo(String modelo){
        return find("SELECT p FROM Processador p WHERE UPPER(p.modelo) LIKE ?1", "%" +modelo.toUpperCase() + "%").list();
    }
}
