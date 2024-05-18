package br.notelab.dto.pessoa.fornecedor;

import org.hibernate.validator.constraints.br.CNPJ;

import br.notelab.dto.pessoa.telefone.TelefoneDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FornecedorDTO(
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 5, max = 40, message = "O nome deve ter 5 e 40 caracteres")
    String nome,
    
    @NotBlank(message = "O email não pode ser nulo ou vazio")
    @Email(message = "O email está em formato inválido!", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    String email,

    @NotBlank(message = "O CNPJ não pode ser nulo ou vazio")
    @CNPJ(message = "O CNPJ está em formato inválido")
    String cnpj,

    @NotNull(message = "O telefone não pode ser nulo")
    @Valid
    TelefoneDTO telefone
) {
    
}
