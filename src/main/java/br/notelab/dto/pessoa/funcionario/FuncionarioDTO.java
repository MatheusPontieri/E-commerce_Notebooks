package br.notelab.dto.pessoa.funcionario;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import br.notelab.dto.endereco.EnderecoDTO;
import br.notelab.dto.pessoa.telefone.TelefoneDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record FuncionarioDTO(
    @NotNull(message = "A data de contrato não pode ser nula")
    @PastOrPresent(message = "A data de contrato não pode estar no futuro")
    LocalDate dataContrato,

    @NotNull(message = "O salário não pode ser nulo")
    @Min(value = 0, message = "O mínimo deve ser 0")
    Double salario,

    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 5, max = 40, message = "O nome deve ter entre 5 e 40 caracteres")
    String nome,

    @NotNull(message = "A data não pode ser nula")
    @Past(message = "A data deve estar no passado")
    LocalDate dataNascimento,
    
    @NotBlank(message = "O cpf não pode ser nulo ou vazio")
    @CPF(message = "O CPF está em formato inválido")
    String cpf,

    @NotBlank(message = "O email não pode ser nulo ou vazio")
    @Email(message = "O email está em formato inválido!", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    String email,

    @NotBlank(message = "A senha não pode ser nula ou vazia")
    @Size(min = 5, max = 20, message = "A senha deve ter entre 5 e 20 caracteres")
    String senha,

    @NotNull(message = "O sexo não pode ser nulo")
    @Min(value = 1, message = "O valor deve ser 1 ou 2")
    @Max(value = 2, message = "O valor deve ser 1 ou 2")
    Integer idSexo,

    @NotNull(message = "O endereço não pode ser nulo")
    @Valid
    List<EnderecoDTO> enderecos,

    @NotNull(message = "O telefone não pode ser nulo")
    @Valid
    List<TelefoneDTO> telefones,

    @NotNull(message = "O perfil não pode ser nulo")
    @Min(value = 1, message = "O valor deve ser 1 ou 2")
    @Max(value = 2, message = "O valor deve ser 1 ou 2")
    Integer idPerfil
) {
    
}