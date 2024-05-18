package br.notelab.dto.notebook.especificacao;

import br.notelab.model.notebook.Especificacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EspecificacaoDTO(
    @Size(min = 3, max = 10, message = "A altura deve ter entre 3 e 10 caracteres")
    String altura,

    @Size(min = 3, max = 10, message = "A altura deve ter entre 3 e 10 caracteres")
    String largura,

    @Size(min = 3, max = 10, message = "A profundidade deve ter entre 3 e 10 caracteres")
    String profundidade,

    @NotBlank(message = "O peso não pode ser nulo ou vazio")
    @Size(min = 3, max = 10, message = "O peso deve ter entre 3 e 10 caracteres")
    String peso
) {
    public static Especificacao convertToEspecificacao(EspecificacaoDTO dto){
        Especificacao e = new Especificacao();
        e.setAltura(dto.altura());
        e.setLargura(dto.largura());
        e.setProfundidade(dto.profundidade());
        e.setPeso(dto.peso());

        return e;
    }
}