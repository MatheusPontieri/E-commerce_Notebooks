package br.notelab.repository;

import java.util.List;

import br.notelab.model.endereco.Estado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado>{
    public List<Estado> findByNome(String nome){
        return find("SELECT e FROM Estado e WHERE UPPER(e.nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public List<Estado> findBySigla(String sigla){
        return find("SELECT e FROM Estado e WHERE UPPER(e.sigla) LIKE ?1", "%" + sigla.toUpperCase() + "%").list();
    }

    public Estado findByNomeCompleto(String nome){
        return find("WHERE UPPER(nome) = ?1", nome.toUpperCase()).firstResult();
    }

    public Estado findBySiglaCompleta(String sigla){
        return find("WHERE UPPER(sigla) = ?1", sigla.toUpperCase()).firstResult();
    }
}