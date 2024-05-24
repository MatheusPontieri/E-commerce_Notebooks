package br.notelab.repository;

import java.util.List;

import br.notelab.model.notebook.memoria.MemoriaRam;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MemoriaRamRepository implements PanacheRepository<MemoriaRam>{
    public List<MemoriaRam> findByCapacidade(String capacidade){
        return find("SELECT m FROM MemoriaRam m WHERE UPPER(m.capacidade) LIKE ?1", "%" + capacidade.toUpperCase() + "%").list();
    }

    public MemoriaRam findByCapacidadeCompleta(String capacidade){
        return find("SELECT m FROM MemoriaRam m WHERE UPPER(m.capacidade) = ?1", capacidade.toUpperCase()).firstResult();
    }
}