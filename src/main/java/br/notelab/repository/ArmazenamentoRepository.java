package br.notelab.repository;

import java.util.List;

import br.notelab.model.notebook.memoria.Armazenamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArmazenamentoRepository implements PanacheRepository<Armazenamento>{
    public List<Armazenamento> findByNome(String nome){
        return find("SELECT a FROM Armazenamento a WHERE UPPER(a.nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public List<Armazenamento> findByCapacidade(String capacidade){
        return find("SELECT a FROM Armazenamento a WHERE UPPER(a.capacidade) LIKE ?1", "%" + capacidade.toUpperCase() + "%").list();
    }

    public Armazenamento findByAllAttributes(String nome, String capacidade){
        return find("WHERE UPPER(nome) = ?1 AND UPPER(capacidade) = ?2", nome.toUpperCase(), capacidade.toUpperCase()).firstResult();
    }
}
