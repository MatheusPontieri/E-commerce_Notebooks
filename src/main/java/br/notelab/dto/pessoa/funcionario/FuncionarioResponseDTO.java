package br.notelab.dto.pessoa.funcionario;

import java.time.LocalDate;

import br.notelab.model.pessoa.Funcionario;

public record FuncionarioResponseDTO(
    Long id,
    LocalDate dataContrato,
    Double salario,
    String nome, 
    LocalDate dataNascimento,
    String cpf,
    String email, 
    String senha 
) {
    public static FuncionarioResponseDTO valueOf(Funcionario f){
        return new FuncionarioResponseDTO(
            f.getId(),
            f.getDataContrato(),
            f.getSalario(),
            f.getPessoa().getNome(),
            f.getPessoa().getDataNascimento(),
            f.getPessoa().getCpf(),
            f.getPessoa().getUsuario().getSenha(),
            f.getPessoa().getUsuario().getSenha()
        );
    }
}
