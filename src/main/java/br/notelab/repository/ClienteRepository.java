package br.notelab.repository;

import java.util.List;

import br.notelab.model.pessoa.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
    public List<Cliente> findByNome(String nome){
        return find("SELECT c FROM Cliente c WHERE UPPER(c.pessoa.nome) LIKE ?1", "%"+nome.toUpperCase()+"%").list();
    }

    public List<Cliente> findByCpf(String cpf){
        return find("SELECT c FROM Cliente c WHERE c.pessoa.cpf LIKE ?1", "%"+ cpf +"%").list();
    }

    public Cliente findByEmailAndSenha(String email, String senha){
        return find("SELECT c FROM Cliente c WHERE c.pessoa.usuario.email = ?1 AND c.pessoa.usuario.senha = ?2", email, senha).firstResult();
    }

    public Cliente findByEmail(String email){
        return find("SELECT c FROM Cliente c WHERE c.pessoa.usuario.email = ?1", email).singleResult();
    }
}