package br.notelab.dto.pessoa.telefone;

import br.notelab.model.pessoa.Telefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TelefoneDTO(
    @NotBlank(message = "O codigo de área não pode ser nulo ou vazio")
    @Size(min = 2, max = 2, message = "O codigo de área deve ter 2 caracteres")
    String codigoArea,

    @NotBlank(message = "O numero não pode ser nulo ou vazio")
    @Size(min = 8, max = 12)
    String numero
) {
    public static Telefone convertToTelefone(TelefoneDTO dto){
        Telefone t = new Telefone();

        t.setCodigoArea(dto.codigoArea);
        t.setNumero(dto.numero());
        return t;
    } 
}
