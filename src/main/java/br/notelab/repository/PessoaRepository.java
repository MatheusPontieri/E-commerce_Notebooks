package br.notelab.repository;

import br.notelab.model.pessoa.Pessoa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa>{
    public Pessoa findByEmailCompleto(String email, Long id) {
        return find("WHERE UPPER(usuario.email) = ?1 AND id != ?2", email.toUpperCase(), id).firstResult();
    }

    public Pessoa findByCpfCompleto(String cpf, Long id){
        return find("WHERE cpf = ?1 AND id != ?2", cpf, id).firstResult();
    }   

    public Pessoa findByTelefoneCompleto(String codigoArea, String numero, Long id){
        return find("SELECT p FROM Pessoa p JOIN p.listaTelefone t WHERE t.codigoArea = ?1 AND t.numero = ?2 AND p.id != ?3", codigoArea, numero, id).firstResult();
    }
}