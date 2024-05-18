package br.notelab.service.pessoa.cliente;

import java.util.List;

import br.notelab.dto.pessoa.cliente.ClienteDTO;
import br.notelab.dto.pessoa.cliente.ClienteResponseDTO;
import br.notelab.dto.pessoa.usuario.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface ClienteService {
    ClienteResponseDTO create(@Valid ClienteDTO dto);
    void update(Long id, @Valid ClienteDTO dto);
    void delete(Long id);
    ClienteResponseDTO findById(Long id);
    List<ClienteResponseDTO> findAll();
    List<ClienteResponseDTO> findByNome(String nome);
    List<ClienteResponseDTO> findByCpf(String cpf);
    UsuarioResponseDTO login(String email, String senha);
} 