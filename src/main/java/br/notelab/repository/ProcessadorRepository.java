package br.notelab.repository;

import java.util.List;

import br.notelab.model.notebook.Processador;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProcessadorRepository implements PanacheRepository<Processador>{
    public List<Processador> findByModelo(String modelo){
        return find("FROM Processador WHERE UPPER(modelo) LIKE ?1", "%" + modelo.toUpperCase() + "%").list();
    }

    public List<Processador> findByGeracao(String geracao){
        return find("FROM Processador WHERE UPPER(modelo) LIKE ?1", "%" + geracao.toUpperCase() + "%").list();
    }
}