package br.notelab.dto.notebook.tela;

import br.notelab.model.notebook.Tela;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TelaDTO(
    @NotBlank(message = "O tamanho não pode ser nulo ou vazio")
    @Size(min = 2, max = 10, message = "O tamanho deve ter entre 2 e 10 caracteres")
    String tamanho,

    @NotBlank(message = "A resolução não pode ser nula ou vazia")
    @Size(min = 2, max = 10, message = "A resolução deve ter entre 2 e 10 caracteres")
    String resolucao,

    @NotBlank(message = "A taxa de atualização não pode ser nula ou vazia")
    @Size(min = 3, max = 10, message = "A taxa de atualização deve ter entre 3 e 30 caracteres")
    String taxaAtualizacao
) {
    public static Tela convertToTela(TelaDTO dto){
        Tela tela = new Tela();
        tela.setTamanho(dto.tamanho());
        tela.setResolucao(dto.resolucao());
        tela.setTaxaAtualizacao(dto.taxaAtualizacao());

        return tela;
    }
}
