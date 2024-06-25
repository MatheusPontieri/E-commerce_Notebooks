package br.notelab.dto.pessoa.cliente;

import java.time.LocalDate;
import java.util.List;

import br.notelab.dto.endereco.EnderecoResponseDTO;
import br.notelab.dto.file.FileInfoResponseDTO;
import br.notelab.dto.pessoa.telefone.TelefoneResponseDTO;
import br.notelab.model.notebook.Notebook;
import br.notelab.model.pessoa.Cliente;

public record ClienteResponseDTO(
    Long id,
    String nome, 
    LocalDate dataNascimento,
    Boolean aceitaMarketing,
    String cpf,
    String email, 
    List<TelefoneResponseDTO> telefones,
    List<EnderecoResponseDTO> enderecos,
    List<Notebook> listaDesejo,
    List<FileInfoResponseDTO> listaNomeImagem
) {
    public static ClienteResponseDTO valueOf(Cliente c){
        return new ClienteResponseDTO(
            c.getId(),
            c.getPessoa().getNome(),
            c.getPessoa().getDataNascimento(),
            c.getAceitaMarketing(),
            c.getPessoa().getCpf(),
            c.getPessoa().getUsuario().getEmail(),
            c.getPessoa().getListaTelefone().stream().map(TelefoneResponseDTO::valueOf).toList(),
            c.getPessoa().getListaEndereco().stream().map(EnderecoResponseDTO::valueOf).toList(),
            c.getListaDesejo(),
            c.getPessoa().getListaNomeImagem().stream().map(FileInfoResponseDTO::valueOf).toList()
        );
    }
}