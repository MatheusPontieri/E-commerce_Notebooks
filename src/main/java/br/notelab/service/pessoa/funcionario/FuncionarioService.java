package br.notelab.service.pessoa.funcionario;

import java.util.List;

import br.notelab.dto.pessoa.funcionario.FuncionarioDTO;
import br.notelab.dto.pessoa.funcionario.FuncionarioResponseDTO;
import br.notelab.dto.pessoa.usuario.EmailPatchDTO;
import br.notelab.dto.pessoa.usuario.SenhaPatchDTO;
import br.notelab.dto.pessoa.usuario.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface FuncionarioService {
    FuncionarioResponseDTO create(@Valid FuncionarioDTO dto);
    void update(Long id, @Valid FuncionarioDTO dto);
    void delete(Long id);
    void updateEmail(Long id, @Valid EmailPatchDTO dto);
    void updateSenha(Long id, @Valid SenhaPatchDTO dto);
    FuncionarioResponseDTO findById(Long id);
    List<FuncionarioResponseDTO> findAll();
    List<FuncionarioResponseDTO> findByNome(String nome);
    List<FuncionarioResponseDTO> findByCpf(String cpf);
    UsuarioResponseDTO loginFuncionario(String email, String senha);
    UsuarioResponseDTO loginAdministrador(String email, String senha);
}