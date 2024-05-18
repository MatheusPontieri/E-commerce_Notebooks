package br.notelab.repository;

import br.notelab.model.pessoa.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    public Usuario findByEmail(String email){
        return find("SELECT u FROM Usuario u WHERE UPPER(u.email) = ?1", email.toUpperCase()).firstResult();
    }
}