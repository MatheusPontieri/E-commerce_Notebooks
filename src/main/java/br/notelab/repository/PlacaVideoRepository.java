package br.notelab.repository;

import java.util.List;

import br.notelab.model.notebook.PlacaVideo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlacaVideoRepository implements PanacheRepository<PlacaVideo>{
    public List<PlacaVideo> findByModelo(String modelo){
        return find("FROM PlacaVideo WHERE UPPER(modelo) LIKE ?1", "%" + modelo.toUpperCase() + "%").list();
    }
}