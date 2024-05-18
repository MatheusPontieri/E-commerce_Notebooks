package br.notelab.repository;

import java.util.List;

import br.notelab.model.notebook.gpu.PlacaVideo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlacaVideoRepository implements PanacheRepository<PlacaVideo> {
    public List<PlacaVideo> findByModelo(String modelo){
        return find("SELECT p FROM PlacaVideo p WHERE UPPER(p.modelo) LIKE ?1", "%"+modelo.toUpperCase()+"%").list();
    }

    public List<PlacaVideo> findByMemoriaVideo(String memoria){
        return find("SELECT p FROM PlacaVideo p WHERE UPPER(p.memoriaVideo) LIKE ?1", "%"+memoria.toUpperCase()+"%").list();
    }

    public PlacaVideo findByAllAttributes(String modelo, String memoria){
        return find("WHERE UPPER(modelo) = ?1 AND UPPER(memoriaVideo) = ?2", modelo.toUpperCase(), memoria.toUpperCase()).firstResult();
    }
}
