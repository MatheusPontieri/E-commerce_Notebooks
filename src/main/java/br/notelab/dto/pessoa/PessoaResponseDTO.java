package br.notelab.dto.pessoa;

import java.time.LocalDate;
import java.util.List;

import br.notelab.dto.endereco.EnderecoResponseDTO;
import br.notelab.dto.pessoa.telefone.TelefoneResponseDTO;
import br.notelab.model.pessoa.Pessoa;
import br.notelab.model.pessoa.Sexo;

public record PessoaResponseDTO(
    Long id,
    String nome,
    LocalDate dataNascimento,
    String cpf,
    Sexo sexo,
    List<EnderecoResponseDTO> listaEndereco,
    TelefoneResponseDTO telefone
) {
    public static PessoaResponseDTO valueOf(Pessoa p){
        return new PessoaResponseDTO(
            p.getId(),
            p.getNome(),
            p.getDataNascimento(),
            p.getCpf(),
            p.getSexo(),
            p.getListaEndereco().stream().map(EnderecoResponseDTO::valueOf).toList(),
            TelefoneResponseDTO.valueOf(p.getTelefone()));
    }
}
