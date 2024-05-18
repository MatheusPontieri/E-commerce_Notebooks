package br.notelab.service.jwt;

import br.notelab.dto.pessoa.usuario.UsuarioResponseDTO;

public interface JwtService {
    String generatedJwt(UsuarioResponseDTO dto, int perfil);
}
