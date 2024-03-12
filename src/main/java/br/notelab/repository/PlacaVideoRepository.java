package br.notelab.repository;

import java.util.List;

import br.notelab.model.PlacaVideo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlacaVideoRepository implements PanacheRepository<PlacaVideo> {
    public List<PlacaVideo> findByNome(String nome){
        return find("SELECT p FROM PlacaVideo p WHERE UPPER(p.modelo) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }
}