package br.notelab.repository;

import java.util.List;

import br.notelab.model.notebook.Notebook;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotebookRepository implements PanacheRepository<Notebook>{
    public List<Notebook> findByDescricao(String descricao){
        return find("SELECT n FROM Notebook n WHERE UPPER(n.descricao) LIKE ?1", "%" + descricao.toUpperCase() + "%").list();
    }

    public List<Notebook> findByPrecoMin(Double preco){
        return find("SELECT n FROM Notebook n WHERE n.preco >= ?1", preco).list();
    }

    public List<Notebook> findByPrecoMax(Double preco){
        return find("SELECT n FROM Notebook n WHERE n.preco <= ?1", preco).list();
    }

    public List<Notebook> findByPrecoMinMax(Double min, Double max){
        return find("SELECT n FROM Notebook n WHERE n.preco >= ?1 AND n.preco <= ?2", min, max).list();
    }

    public List<Notebook> findBySistemaOperacional(String sistema){
        return find("SELECT n FROM Notebook n WHERE UPPER(n.sistemaOperacional) LIKE ?1", "%" + sistema.toUpperCase() + "%").list();
    }

    public List<Notebook> findByGamer(Boolean isGamer){
        return find("SELECT n FROM Notebook n WHERE n.isGamer = ?1", isGamer).list();
    }

    public List<Notebook> findByRecurso(String recurso){
        return find("SELECT n FROM Notebook n JOIN n.listaRecurso r WHERE UPPER(r.nome) LIKE ?1", "%" + recurso.toUpperCase() + "%").list();
    }

    public List<Notebook> findByPlacaVideo(String placaVideo){
        return find("SELECT n FROM Notebook n WHERE UPPER(n.placaVideo.modelo) LIKE ?1", "%" + placaVideo.toUpperCase() + "%").list();
    }

    public List<Notebook> findByProcessador(String processador){
        return find("SELECT n FROM Notebook n WHERE UPPER(n.processador.modelo) LIKE ?1", "%" + processador.toUpperCase() + "%").list();
    }

    public List<Notebook> findByNomeArmazenamento(String nome){
        return find("SELECT n FROM Notebook n JOIN n.listaArmazenamento a WHERE UPPER(a.nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public List<Notebook> findByCapacidadeArmazenamento(String capacidade){
        return find("SELECT n FROM Notebook n JOIN n.listaArmazenamento a WHERE UPPER(a.capacidade) LIKE ?1", "%" + capacidade.toUpperCase() + "%").list();
    }

    public List<Notebook> findByCapacidadeMemoriaRam(String capacidade){
        return find("SELECT n FROM Notebook n WHERE UPPER(n.memoriaRam.capacidade) LIKE ?1", "%" + capacidade.toUpperCase() + "%").list();
    }

    public List<Notebook> findByTaxaAtualizacao(String taxa){
        return find("SELECT n FROM Notebook n WHERE UPPER(n.tela.taxaAtualizacao) LIKE ?1", "%" + taxa.toUpperCase() + "%").list();
    }

    public List<Notebook> findByConexaoSemFio(String conexao){
        return find("SELECT n FROM Notebook n JOIN n.listaConexao c WHERE UPPER(c.nome) LIKE ?1", "%" + conexao.toUpperCase() + "%").list();
    }

    public List<Notebook> findByEntradaSaida(String entradaSaida){
        return find("SELECT n FROM Notebook n JOIN n.listaEntradaSaida es WHERE UPPER(es.nome) LIKE ?1", "%" + entradaSaida.toUpperCase() + "%").list();
    }

    public Notebook findByModeloCompleto(String modelo, Long id){
        return find("WHERE UPPER(modelo) = ?1 AND id != ?2", modelo.toUpperCase(), id).firstResult();
    }
}