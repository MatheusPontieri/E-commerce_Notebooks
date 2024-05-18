package br.notelab.dto.notebook;

import java.util.List;

import br.notelab.dto.notebook.conexao.ConexaoSemFioResponseDTO;
import br.notelab.dto.notebook.conexao.EntradaSaidaResponseDTO;
import br.notelab.dto.notebook.especificacao.EspecificacaoResponseDTO;
import br.notelab.dto.notebook.gpu.PlacaVideoResponseDTO;
import br.notelab.dto.notebook.memoria.ArmazenamentoResponseDTO;
import br.notelab.dto.notebook.memoria.MemoriaRamResponseDTO;
import br.notelab.dto.notebook.processador.ProcessadorResponseDTO;
import br.notelab.dto.notebook.recurso.RecursoResponseDTO;
import br.notelab.dto.notebook.tela.TelaResponseDTO;
import br.notelab.dto.pessoa.fornecedor.FornecedorResponseDTO;
import br.notelab.model.notebook.Notebook;
import br.notelab.model.notebook.gpu.TipoPlacaVideo;

public record NotebookResponseDTO(
    Long id,
    String descricao,
    String linha,
    String serie,
    Double preco,
    String modelo,
    String sistemaOperacional,
    Boolean isGamer,
    Integer numUsb,
    Integer estoque,
    FornecedorResponseDTO fornecedor,
    List<RecursoResponseDTO> listaRecurso,
    PlacaVideoResponseDTO placaVideo,
    ProcessadorResponseDTO processador,
    List<ArmazenamentoResponseDTO> listaArmazenamento,
    MemoriaRamResponseDTO memoriaRam,
    TelaResponseDTO tela,
    EspecificacaoResponseDTO especificacao,
    List<ConexaoSemFioResponseDTO> listaConexaoSemFio,
    List<EntradaSaidaResponseDTO> listaEntradaSaida,
    TipoPlacaVideo tipoPlacaVideo
) {
    public static NotebookResponseDTO valueOf(Notebook n) {
        return new NotebookResponseDTO(
            n.getId(), 
            n.getDescricao(), 
            n.getLinha(), 
            n.getSerie(), 
            n.getPreco(), 
            n.getModelo(), 
            n.getSistemaOperacional(), 
            n.getIsGamer(),
            n.getNumUsb(),
            n.getEstoque(),
            FornecedorResponseDTO.valueOf(n.getFornecedor()), 
            n.getListaRecurso().stream().map(r -> RecursoResponseDTO.valueOf(r)).toList(), 
            PlacaVideoResponseDTO.valueOf(n.getPlacaVideo()), 
            ProcessadorResponseDTO.valueOf(n.getProcessador()), 
            n.getListaArmazenamento().stream().map(a -> ArmazenamentoResponseDTO.valueOf(a)).toList(),
            MemoriaRamResponseDTO.valueOf(n.getMemoriaRam()), 
            TelaResponseDTO.valueOf(n.getTela()), 
            EspecificacaoResponseDTO.valueOf(n.getEspecificacao()), 
            n.getListaConexao().stream().map(c -> ConexaoSemFioResponseDTO.valueOf(c)).toList(), 
            n.getListaEntradaSaida().stream().map(e -> EntradaSaidaResponseDTO.valueOf(e)).toList(), 
            n.getTipoPlacaVideo()
        );
    }
}