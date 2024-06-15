package br.notelab.service.jwt;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import br.notelab.dto.pessoa.usuario.UsuarioResponseDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generatedJwt(UsuarioResponseDTO dto, int perfil) {
        Instant now = Instant.now();
        Instant expiryDate = now.plus(EXPIRATION_TIME);

        Set<String> roles = new HashSet<>();

        switch (perfil) {
           case 1 -> roles.add("Cliente");
           case 2 -> roles.add("Funcionario");
           case 3 -> roles.add("Administrador");
        }
        
        return Jwt.issuer("notelab-jwt")
                .subject(dto.email())
                .groups(roles)
                .expiresAt(expiryDate)
                .sign();
    }
}