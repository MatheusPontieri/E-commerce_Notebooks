package br.notelab.repository;

import java.util.List;

import br.notelab.model.pessoa.Fornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor> {
    public List<Fornecedor> findByNome(String nome){
        return find("SELECT f FROM Fornecedor f WHERE UPPER(f.nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public Fornecedor findByEmailCompleto(String email, Long id) {
        return find("WHERE UPPER(email) = ?1 AND id != ?2", email.toUpperCase(), id).firstResult();
    }

    public Fornecedor findByCnpjCompleto(String cnpj, Long id){
        return find("WHERE cnpj = ?1 AND id != ?2", cnpj, id).firstResult();
    }   

    public Fornecedor findByTelefoneCompleto(String codigoArea, String numero, Long id){
        return find("WHERE telefone.codigoArea = ?1 AND telefone.numero = ?2 AND id != ?3", codigoArea, numero, id).firstResult();
    }
}