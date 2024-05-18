package br.notelab.dto.pessoa.cliente;

import java.time.LocalDate;
import br.notelab.model.pessoa.Cliente;

public record ClienteResponseDTO(
    Long id,
    String nome, 
    LocalDate dataNascimento,
    Boolean aceitaMarketing,
    String cpf,
    String email, 
    String senha
) {
    public static ClienteResponseDTO valueOf(Cliente c){
        return new ClienteResponseDTO(
            c.getId(),
            c.getPessoa().getNome(),
            c.getPessoa().getDataNascimento(),
            c.getAceitaMarketing(),
            c.getPessoa().getCpf(),
            c.getPessoa().getUsuario().getSenha(),
            c.getPessoa().getUsuario().getSenha()
        );
    }
}
