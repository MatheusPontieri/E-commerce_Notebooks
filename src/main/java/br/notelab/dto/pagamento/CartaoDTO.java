package br.notelab.dto.pagamento;

import br.notelab.model.pagamento.Cartao;
import br.notelab.model.pagamento.ModalidadeCartao;

import java.time.LocalDate;
import org.hibernate.validator.constraints.br.CPF;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CartaoDTO(

    @NotBlank(message = "O nome do titular não pode ser vazio ou nulo")
    @Size(min = 5, max = 30, message = "O nome do titular deve ter entre 5 e 30 caracteres")
    String titularCartao,

    @NotBlank(message = "O CPF não pode ser nulo ou vazio")
    @CPF(message = "O CPF está em formato inválido")
    String cpfCartao,

    @NotBlank(message = "O número do cartão não pode ser nulo ou vazio")
    @Size(min = 14, max = 20, message = "O número do cartão deve ter entre 14 e 20 caracteres")
    String numero,

    @NotNull(message = "A data de validade não pode ser nula")
    @Future(message = "A data de validade deve estar no futuro")
    LocalDate dataValidade,

    @NotNull(message = "O código de segurança não pode ser nulo")
    @Min(value = 3, message = "O código de segurança deve ter no mínimo 3 dígitos")
    @Max(value = 4, message = "O código de segurana deve ter no máximo 4 dígitos")
    Integer codigoSeguranca,

    @NotNull(message = "A modalidade do cartão não pode ser nula")
    @Min(value = 1, message = "O valor deve ser 1 ou 2")
    @Max(value = 2, message = "O valor deve ser 1 ou 2")
    Integer idModalidade
    ) {

    public static Cartao convertToCartao(CartaoDTO dto){
        Cartao c = new Cartao();

        c.setTitular(dto.titularCartao());
        c.setCpfCartao(dto.cpfCartao());
        c.setNumero(dto.numero());
        c.setDataValidade(dto.dataValidade());
        c.setCodigoSeguranca(dto.codigoSeguranca());
        c.setModalidade(ModalidadeCartao.valueOf(dto.idModalidade()));

        return c;
    }
}