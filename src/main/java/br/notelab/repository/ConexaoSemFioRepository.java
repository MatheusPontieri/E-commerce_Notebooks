package br.notelab.repository;

import java.util.List;

import br.notelab.model.notebook.conexao.ConexaoSemFio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConexaoSemFioRepository implements PanacheRepository<ConexaoSemFio> {
    public List<ConexaoSemFio> findByNome(String nome){
        return find("SELECT c FROM ConexaoSemFio c WHERE UPPER(c.nome) LIKE ?1", "%"+nome.toUpperCase()+"%").list();
    }

    public ConexaoSemFio findByNomeCompleto(String nome){
        return find("WHERE UPPER(nome) = ?1", nome.toUpperCase()).firstResult();
    }
}
