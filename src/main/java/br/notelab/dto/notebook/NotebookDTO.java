package br.notelab.dto.notebook;

import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import br.notelab.dto.notebook.especificacao.EspecificacaoDTO;
import br.notelab.dto.notebook.tela.TelaDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record NotebookDTO( // Linha, serie, todas as listas, placa video, 
    @NotBlank(message = "A descrição não pode ser nula ou vazia")
    @Size(min = 10, max = 140, message = "A descrição deve ter entre 10 e 140 caracteres")
    String descricao,

    @Size(min = 3, max = 15, message = "A linha deve ter entre 3 e 15 caracteres")
    String linha,

    @Size(min = 3, max = 15, message = "A série deve ter entre 3 e 15 caracteres")
    String serie,

    @NotNull(message = "O preço não pode ser nulo")
    @Digits(integer = 6, fraction = 2, message = "O preço deve ter no máximo 6 algarismos como inteiro e 2 como decimal")
    @PositiveOrZero(message = "O preço deve ser maior ou igual a 0")
    Double preco,

    @NotBlank(message = "O modelo não pode ser nulo ou vazio")
    @Size(min = 3, max = 20, message = "O modelo deve ter entre 3 e 20 caracteres")
    String modelo,

    @NotBlank(message = "O sistema operacional não pode ser nulo ou vazio")
    @Size(min = 3, max = 20, message = "O sistema operacional deve ter entre 3 e 20 caracteres")
    String sistemaOperacional,

    @NotNull(message = "A classificação de gamer não pode ser nula")
    Boolean isGamer,

    @NotNull(message = "O número de Usb's não pode ser nulo")
    @PositiveOrZero(message = "O número deve ser maior ou igual a 0")
    Integer numUsb,

    @NotBlank(message = "O limite de expansão não pode ser nulo ou vazio")
    @Size(min = 3, max = 10, message = "O limite de expansão deve ter entre 3 e 10 caracteres")
    String limiteRam,

    @NotNull(message = "O estoque não pode ser nulo")
    @PositiveOrZero(message = "O estoque não pode ser negativo")
    Integer estoque,

    @NotNull(message = "O id do fornecedor não pode ser nulo")
    @Positive(message = "O id do fornecedor não pode ser negativo ou 0")
    Long idFornecedor,

    @UniqueElements(message = "A lista de recursos não deve possuir elementos repetidos")
    List<Long> listaIdRecurso,

    @Positive(message = "O id da placa de video não pode ser negativo ou 0")
    Long idPlacaVideo,

    @NotNull(message = "O processador não pode ser nulo")
    @Positive(message = "O id do processador não pode ser negativo ou 0")
    Long idProcessador,

    @NotNull(message = "A lista de armazenamentos não pode ser nula")
    @UniqueElements(message = "A lista de armazenamentos não deve possuir elementos repetidos")
    List<Long> listaIdArmazenamento,

    @NotNull(message = "A lista de memorias ram não pode ser nula")
    @UniqueElements(message = "A lista de memorias ram não deve possuir elementos repetidos")
    List<Long> listaIdMemoriasRam,

    @NotNull(message = "A tela não pode ser nula")
    @Valid
    TelaDTO tela,

    @NotNull(message = "A especificação não pode ser nula")
    @Valid
    EspecificacaoDTO especificacao,

    @UniqueElements(message = "A lista de conexoes não deve possuir elementos repetidos")
    List<Long> listaIdConexaoSemFio,

    @UniqueElements(message = "A lista de entradas e saidas não deve possuir elementos repetidos")
    List<Long> listaIdEntradaSaida,

    @NotNull(message = "O tipo da placa de vídeo não pode ser nulo")
    @Min(value = 1, message = "O valor deve ser 1 ou 2")
    @Max(value = 2, message = "O valor deve ser 1 ou 2")
    Integer idTipoPlacaVideo
) {
}