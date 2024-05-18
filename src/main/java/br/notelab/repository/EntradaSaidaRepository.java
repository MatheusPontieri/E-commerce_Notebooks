package br.notelab.repository;

import java.util.List;

import br.notelab.model.notebook.conexao.EntradaSaida;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EntradaSaidaRepository implements PanacheRepository<EntradaSaida> {
    public List<EntradaSaida> findByNome(String nome){
        return find("SELECT e FROM EntradaSaida e WHERE UPPER(e.nome) LIKE ?1", "%"+nome.toUpperCase()+"%").list();
    }
    
    public EntradaSaida findByNomeCompleto(String nome){
        return find("WHERE UPPER(nome) = ?1", nome.toUpperCase()).firstResult();
    }
}
