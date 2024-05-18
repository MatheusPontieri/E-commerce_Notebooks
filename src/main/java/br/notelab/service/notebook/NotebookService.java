package br.notelab.service.notebook;

import java.util.List;

import br.notelab.dto.notebook.NotebookDTO;
import br.notelab.dto.notebook.NotebookResponseDTO;
import jakarta.validation.Valid;

public interface NotebookService {
    NotebookResponseDTO create(@Valid NotebookDTO dto);
    void update(Long id, @Valid NotebookDTO dto);
    void delete(Long id);
    NotebookResponseDTO findById(Long id);
    List<NotebookResponseDTO> findAll();
    List<NotebookResponseDTO> findByDescricao(String descricao);
    List<NotebookResponseDTO> findByPrecoMin(Double preco);
    List<NotebookResponseDTO> findByPrecoMax(Double preco);
    List<NotebookResponseDTO> findByPrecoMinMax(Double min, Double max);
    List<NotebookResponseDTO> findBySistemaOperacional(String sistema);
    List<NotebookResponseDTO> findByGamer(Boolean isGamer);
    List<NotebookResponseDTO> findByRecurso(String recurso);
    List<NotebookResponseDTO> findByPlacaVideo(String placaVideo);
    List<NotebookResponseDTO> findByProcessador(String processador);
    List<NotebookResponseDTO> findByNomeArmazenamento(String nome);
    List<NotebookResponseDTO> findByCapacidadeArmazenamento(String capacidade);
    List<NotebookResponseDTO> findByCapacidadeMemoriaRam(String capacidade);
    List<NotebookResponseDTO> findByTaxaAtualizacao(String taxa);
    List<NotebookResponseDTO> findByConexaoSemFio(String conexao);
    List<NotebookResponseDTO> findByEntradaSaida(String entradaSaida);
}
