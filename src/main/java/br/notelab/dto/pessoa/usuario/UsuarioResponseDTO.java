package br.notelab.dto.pessoa.usuario;

import br.notelab.model.pessoa.Pessoa;

public record UsuarioResponseDTO(
    String nome,
    String email
) {
    public static UsuarioResponseDTO valueOf(Pessoa p){
        return new UsuarioResponseDTO(p.getNome(), p.getUsuario().getEmail());
    }
}
