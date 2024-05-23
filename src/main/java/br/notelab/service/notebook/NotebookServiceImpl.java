package br.notelab.service.notebook;

import java.util.List;

import br.notelab.dto.notebook.NotebookDTO;
import br.notelab.dto.notebook.NotebookResponseDTO;
import br.notelab.dto.notebook.especificacao.EspecificacaoDTO;
import br.notelab.dto.notebook.tela.TelaDTO;
import br.notelab.model.notebook.Especificacao;
import br.notelab.model.notebook.Notebook;
import br.notelab.model.notebook.Tela;
import br.notelab.model.notebook.gpu.TipoPlacaVideo;
import br.notelab.repository.ArmazenamentoRepository;
import br.notelab.repository.ConexaoSemFioRepository;
import br.notelab.repository.EntradaSaidaRepository;
import br.notelab.repository.FornecedorRepository;
import br.notelab.repository.MemoriaRamRepository;
import br.notelab.repository.NotebookRepository;
import br.notelab.repository.PlacaVideoRepository;
import br.notelab.repository.ProcessadorRepository;
import br.notelab.repository.RecursoRepository;
import br.notelab.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class NotebookServiceImpl implements NotebookService {

    @Inject
    public NotebookRepository notebookRepository;

    @Inject
    public FornecedorRepository fornecedorRepository;

    @Inject
    public RecursoRepository recursoRepository;

    @Inject
    public PlacaVideoRepository placaVideoRepository;

    @Inject
    public ProcessadorRepository processadorRepository;

    @Inject
    public ArmazenamentoRepository armazenamentoRepository;

    @Inject
    public MemoriaRamRepository memoriaRamRepository;

    @Inject
    public ConexaoSemFioRepository conexaoSemFioRepository;

    @Inject
    public EntradaSaidaRepository entradaSaidaRepository;

    @Override
    @Transactional
    public NotebookResponseDTO create(@Valid NotebookDTO dto) {
        validarModeloNotebook(dto.modelo(), 0L);

        Notebook n = new Notebook();
        n.setDescricao(dto.descricao());
        n.setLinha(dto.linha());
        n.setSerie(dto.serie());
        n.setPreco(dto.preco());
        n.setModelo(dto.modelo());
        n.setSistemaOperacional(dto.sistemaOperacional());
        n.setIsGamer(dto.isGamer());
        n.setNumUsb(dto.numUsb());
        n.setLimiteRam(dto.limiteRam());
        n.setEstoque(dto.estoque());
        n.setFornecedor(fornecedorRepository.findById(dto.idFornecedor()));
        n.setListaRecurso(dto.listaIdRecurso().stream().map(id -> recursoRepository.findById(id)).toList());
        n.setPlacaVideo(placaVideoRepository.findById(dto.idPlacaVideo()));
        n.setProcessador(processadorRepository.findById(dto.idProcessador()));
        n.setListaArmazenamento(dto.listaIdArmazenamento().stream().map(a -> armazenamentoRepository.findById(a)).toList());
        n.setListaMemoriaRam(dto.listaIdMemoriasRam().stream().map(m -> memoriaRamRepository.findById(m)).toList());
        n.setTela(TelaDTO.convertToTela(dto.tela()));
        n.setEspecificacao(EspecificacaoDTO.convertToEspecificacao(dto.especificacao()));
        n.setListaConexao(dto.listaIdConexaoSemFio().stream().map(c -> conexaoSemFioRepository.findById(c)).toList());
        n.setListaEntradaSaida(dto.listaIdEntradaSaida().stream().map(e -> entradaSaidaRepository.findById(e)).toList());
        n.setTipoPlacaVideo(TipoPlacaVideo.valueOf(dto.idTipoPlacaVideo()));

        notebookRepository.persist(n);
        return NotebookResponseDTO.valueOf(n);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid NotebookDTO dto) {
        Notebook n = notebookRepository.findById(id);

        validarModeloNotebook(dto.modelo(), n.getId());

        n.setDescricao(dto.descricao());
        n.setLinha(dto.linha());
        n.setSerie(dto.serie());
        n.setPreco(dto.preco());
        n.setModelo(dto.modelo());
        n.setSistemaOperacional(dto.sistemaOperacional());
        n.setIsGamer(dto.isGamer());
        n.setNumUsb(dto.numUsb());
        n.setLimiteRam(dto.limiteRam());
        n.setEstoque(dto.estoque());
        n.setFornecedor(fornecedorRepository.findById(dto.idFornecedor()));
        n.setListaRecurso(dto.listaIdRecurso().stream().map(r -> recursoRepository.findById(r)).toList());
        n.setPlacaVideo(placaVideoRepository.findById(dto.idPlacaVideo()));
        n.setProcessador(processadorRepository.findById(dto.idProcessador()));
        n.setListaArmazenamento(dto.listaIdArmazenamento().stream().map(a -> armazenamentoRepository.findById(a)).toList());
        n.setListaMemoriaRam(dto.listaIdMemoriasRam().stream().map(m -> memoriaRamRepository.findById(m)).toList());
        updateInstanceOfTela(n.getTela(), dto.tela());
        updateInstanceOfEspecificacao(n.getEspecificacao(), dto.especificacao());
        n.setListaConexao(dto.listaIdConexaoSemFio().stream().map(c -> conexaoSemFioRepository.findById(c)).toList());
        n.setListaEntradaSaida(dto.listaIdEntradaSaida().stream().map(e -> entradaSaidaRepository.findById(e)).toList());
        n.setTipoPlacaVideo(TipoPlacaVideo.valueOf(dto.idTipoPlacaVideo()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        notebookRepository.deleteById(id);
    }

    @Override
    public NotebookResponseDTO findById(Long id) {
        return NotebookResponseDTO.valueOf(notebookRepository.findById(id));
    }

    @Override
    public List<NotebookResponseDTO> findAll() {
        return notebookRepository.listAll().stream().map(n -> NotebookResponseDTO.valueOf(n)).toList();
    }

    @Override
    public List<NotebookResponseDTO> findByDescricao(String descricao) {
        return notebookRepository.findByDescricao(descricao).stream().map(n -> NotebookResponseDTO.valueOf(n)).toList();

    }

    @Override
    public List<NotebookResponseDTO> findByPrecoMin(Double preco) {
        return notebookRepository.findByPrecoMin(preco).stream().map(NotebookResponseDTO::valueOf).toList();
    }

    @Override
    public List<NotebookResponseDTO> findByPrecoMax(Double preco) {
        return notebookRepository.findByPrecoMax(preco).stream().map(NotebookResponseDTO::valueOf).toList();
    }

    @Override
    public List<NotebookResponseDTO> findByPrecoMinMax(Double min, Double max) {
        return notebookRepository.findByPrecoMinMax(min, max).stream().map(NotebookResponseDTO::valueOf).toList();
    }

    @Override
    public List<NotebookResponseDTO> findBySistemaOperacional(String sistema) {
        return notebookRepository.findBySistemaOperacional(sistema).stream().map(NotebookResponseDTO::valueOf).toList();
    }

    @Override
    public List<NotebookResponseDTO> findByGamer(Boolean isGamer) {
        return notebookRepository.findByGamer(isGamer).stream().map(NotebookResponseDTO::valueOf).toList();
    }

    @Override
    public List<NotebookResponseDTO> findByRecurso(String recurso) {
        return notebookRepository.findByRecurso(recurso).stream().map(NotebookResponseDTO::valueOf).toList();
    }

    @Override
    public List<NotebookResponseDTO> findByPlacaVideo(String placaVideo) {
        return notebookRepository.findByPlacaVideo(placaVideo).stream().map(NotebookResponseDTO::valueOf).toList();
    }

    @Override
    public List<NotebookResponseDTO> findByProcessador(String processador) {
        return notebookRepository.findByProcessador(processador).stream().map(NotebookResponseDTO::valueOf).toList();
    }

    @Override
    public List<NotebookResponseDTO> findByNomeArmazenamento(String nome) {
        return notebookRepository.findByNomeArmazenamento(nome).stream().map(NotebookResponseDTO::valueOf).toList();
    }

    @Override
    public List<NotebookResponseDTO> findByCapacidadeArmazenamento(String capacidade) {
        return notebookRepository.findByCapacidadeArmazenamento(capacidade).stream().map(NotebookResponseDTO::valueOf).toList();
    }

    @Override
    public List<NotebookResponseDTO> findByCapacidadeMemoriaRam(String capacidade) {
        return notebookRepository.findByCapacidadeMemoriaRam(capacidade).stream().map(NotebookResponseDTO::valueOf).toList();
    }

    @Override
    public List<NotebookResponseDTO> findByTaxaAtualizacao(String taxa) {
        return notebookRepository.findByTaxaAtualizacao(taxa).stream().map(NotebookResponseDTO::valueOf).toList();
    }

    @Override
    public List<NotebookResponseDTO> findByConexaoSemFio(String conexao) {
        return notebookRepository.findByConexaoSemFio(conexao).stream().map(NotebookResponseDTO::valueOf).toList();
    }

    @Override
    public List<NotebookResponseDTO> findByEntradaSaida(String entradaSaida) {
        return notebookRepository.findByEntradaSaida(entradaSaida).stream().map(NotebookResponseDTO::valueOf).toList();
    }
    
    private void updateInstanceOfTela(Tela t, TelaDTO dto){
        t.setTamanho(dto.tamanho());
        t.setResolucao(dto.resolucao());
        t.setTaxaAtualizacao(dto.taxaAtualizacao());
    }

    private void updateInstanceOfEspecificacao(Especificacao e, EspecificacaoDTO dto){
        e.setAltura(dto.altura());
        e.setLargura(dto.largura());
        e.setProfundidade(dto.profundidade());
        e.setPeso(dto.peso());
    }

    private void validarModeloNotebook(String modelo, Long id){
        if(notebookRepository.findByModeloCompleto(modelo, id) != null)
            throw new ValidationException("modelo", "O modelo "+modelo+" já existe");
    }
}
