package br.notelab.dto.pessoa.funcionario;

import java.time.LocalDate;
import java.util.List;

import br.notelab.dto.endereco.EnderecoResponseDTO;
import br.notelab.dto.file.FileInfoResponseDTO;
import br.notelab.dto.pessoa.telefone.TelefoneResponseDTO;
import br.notelab.model.pessoa.funcionario.Funcionario;

public record FuncionarioResponseDTO(
    Long id,
    String nome, 
    LocalDate dataContrato,
    Double salario,
    LocalDate dataNascimento,
    String cpf,
    String email, 
    List<TelefoneResponseDTO> telefones,
    List<EnderecoResponseDTO> enderecos,
    List<FileInfoResponseDTO> listaNomeImagem
) {
    public static FuncionarioResponseDTO valueOf(Funcionario f){
        return new FuncionarioResponseDTO(
            f.getId(),
            f.getPessoa().getNome(),
            f.getDataContrato(),
            f.getSalario(),
            f.getPessoa().getDataNascimento(),
            f.getPessoa().getCpf(),
            f.getPessoa().getUsuario().getEmail(),
            f.getPessoa().getListaTelefone().stream().map(TelefoneResponseDTO::valueOf).toList(),
            f.getPessoa().getListaEndereco().stream().map(EnderecoResponseDTO::valueOf).toList(),
            f.getPessoa().getListaNomeImagem().stream().map(FileInfoResponseDTO::valueOf).toList()
        );
    }
}
