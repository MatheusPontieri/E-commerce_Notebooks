package br.notelab.repository;

import java.util.List;

import br.notelab.model.endereco.Cidade;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CidadeRepository implements PanacheRepository<Cidade>{
    public List<Cidade> findByNome(String nome){
        return find("SELECT c FROM Cidade c WHERE UPPER(c.nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public List<Cidade> findByEstado(String nome){
        return find("SELECT c FROM Cidade c WHERE UPPER(c.estado.nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public Cidade findByAllAtributtes(String cidade, Long idEstado){
        return find("WHERE UPPER(nome) = ?1 AND estado.id = ?2", cidade.toUpperCase(), idEstado).firstResult();
    }
}