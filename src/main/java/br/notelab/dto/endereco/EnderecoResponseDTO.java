package br.notelab.dto.endereco;

import br.notelab.dto.endereco.cidade.CidadeResponseDTO;
import br.notelab.model.endereco.Endereco;

public record EnderecoResponseDTO(
    String cep,
    String logradouro,
    String bairro,
    int numero,
    String complemento,
    CidadeResponseDTO cidade
) {
    public static EnderecoResponseDTO valueOf(Endereco e){
        return new EnderecoResponseDTO(
            e.getCep(),
            e.getLogradouro(),
            e.getBairro(),
            e.getNumero(),
            e.getComplemento(),
            CidadeResponseDTO.valueOf(e.getCidade()));
    }
}
