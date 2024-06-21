package br.notelab.resource;

import org.jboss.logging.Logger;

import br.notelab.dto.pessoa.usuario.AuthUsuarioDTO;
import br.notelab.dto.pessoa.usuario.UsuarioResponseDTO;
import br.notelab.service.hash.HashService;
import br.notelab.service.jwt.JwtService;
import br.notelab.service.pessoa.cliente.ClienteService;
import br.notelab.service.pessoa.funcionario.FuncionarioService;
import br.notelab.validation.ValidationException;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    private static final Logger LOG = Logger.getLogger(AuthResource.class);
    
    @Inject
    public ClienteService clienteService;

    @Inject
    public FuncionarioService funcionarioService;

    @Inject
    public HashService hashService;

    @Inject
    public JwtService jwtService; 

    @POST
    public Response login(@Valid AuthUsuarioDTO dto) {
        String hash = hashService.getHashSenha(dto.senha());
        UsuarioResponseDTO usuario = null;

        try {
            if(dto.perfil() == 1){
                usuario = clienteService.login(dto.email(), hash);
                LOG.infof("Cliente com email %s fez login no sistema", usuario.email());
            }
            else if(dto.perfil() == 2){
                usuario = funcionarioService.login(dto.email(), hash);
                LOG.infof("Funcionario com email %s fez login no sistema", usuario.email());
            }
            else
                return Response.status(Status.NOT_FOUND).build();
        } catch (Exception e) {
            throw new ValidationException("email, senha, perfil", "login inválido");
        }

        return Response.ok()
            .header("Authorization", jwtService.generatedJwt(usuario, dto.perfil()))
            .build();
    }
}
