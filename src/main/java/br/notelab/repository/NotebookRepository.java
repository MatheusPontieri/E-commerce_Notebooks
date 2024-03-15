package br.notelab.repository;

import java.util.List;

import br.notelab.model.notebook.Notebook;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotebookRepository implements PanacheRepository<Notebook>{
    public List<Notebook> findByDescricao(String descricao){
        return find("FROM Notebook WHERE UPPER(descricao) LIKE ?1", "%" + descricao.toUpperCase() + "%").list();
    }
}