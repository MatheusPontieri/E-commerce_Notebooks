package br.notelab.repository;

import java.util.List;

import br.notelab.model.pessoa.funcionario.Funcionario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepository<Funcionario> {
    public List<Funcionario> findByNome(String nome){
        return find("SELECT f FROM Funcionario f WHERE UPPER(f.pessoa.nome) LIKE ?1", "%"+nome.toUpperCase()+"%").list();
    }

    public List<Funcionario> findByCpf(String cpf){
        return find("SELECT f FROM Funcionario f WHERE f.pessoa.cpf LIKE ?1", "%"+cpf+"%").list();
    }

    public Funcionario findFuncionarioByEmailAndSenha(String email, String senha){
        return find("SELECT f FROM Funcionario f WHERE f.pessoa.usuario.email = ?1 AND f.pessoa.usuario.senha = ?2 AND f.perfil = 1", email, senha).firstResult();
    }

    public Funcionario findAdministradorByEmailAndSenha(String email, String senha){
        return find("SELECT f FROM Funcionario f WHERE f.pessoa.usuario.email = ?1 AND f.pessoa.usuario.senha = ?2 AND f.perfil = 2", email, senha).firstResult();
    }
}