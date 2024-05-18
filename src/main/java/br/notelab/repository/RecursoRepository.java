package br.notelab.repository;

import java.util.List;

import br.notelab.model.notebook.Recurso;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecursoRepository implements PanacheRepository<Recurso>{
    public List<Recurso> findByNome(String nome){
        return find("SELECT r FROM Recurso r WHERE UPPER(r.nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public Recurso findByNomeCompleto(String nome){
        return find("WHERE UPPER(nome) = ?1", nome.toUpperCase()).firstResult();
    }
}
