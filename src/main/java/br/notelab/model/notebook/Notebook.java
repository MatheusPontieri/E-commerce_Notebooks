package br.notelab.model.notebook;

import java.util.List;

import br.notelab.model.DefaultEntity;
import br.notelab.model.notebook.conexao.ConexaoSemFio;
import br.notelab.model.notebook.conexao.EntradaSaida;
import br.notelab.model.notebook.gpu.PlacaVideo;
import br.notelab.model.notebook.gpu.TipoPlacaVideo;
import br.notelab.model.notebook.memoria.Armazenamento;
import br.notelab.model.notebook.memoria.MemoriaRam;
import br.notelab.model.pessoa.Fornecedor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Notebook extends DefaultEntity {
    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, unique = true)
    private String modelo;

    private String linha;

    private String serie;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false, name = "sistema_operacional")
    private String sistemaOperacional;

    @Column(nullable = false, name = "gamer")
    private Boolean isGamer;

    @Column(nullable = false, name = "num_usb")
    private Integer numUsb;

    @Column(nullable = false, name = "limite_ram")
    private String limiteRam;

    @Column(nullable = false)
    private Integer estoque;

    @Column(name = "nome_imagem")
    private String nomeImagem;

    @ManyToMany
    @JoinTable(name="notebook_recurso",
               joinColumns = @JoinColumn(name = "id_notebook"),
               inverseJoinColumns = @JoinColumn(name="id_recurso"))
    private List<Recurso> listaRecurso;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "id_placa_video")    
    private PlacaVideo placaVideo;

    @ManyToOne
    @JoinColumn(name = "id_processador", nullable = false)    
    private Processador processador;

    @ManyToMany
    @JoinTable(name = "notebook_armazenamento",
                joinColumns = @JoinColumn(name = "id_notebook"),
                inverseJoinColumns = @JoinColumn(name = "id_armazenamento"))
    private List<Armazenamento> listaArmazenamento;

    @ManyToMany
    @JoinTable(name = "notebook_memoria_ram",
               joinColumns = @JoinColumn(name = "id_notebook"),
               inverseJoinColumns = @JoinColumn(name = "id_memoria_ram"))
    private List<MemoriaRam> listaMemoriaRam;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_tela", nullable = false, unique = true)
    private Tela tela;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_especificacao", nullable = false, unique = true)
    private Especificacao especificacao;

    @ManyToMany
    @JoinTable(name = "notebook_conexao",
               joinColumns = @JoinColumn(name = "id_notebook"),
               inverseJoinColumns = @JoinColumn(name = "id_conexao"))
    private List<ConexaoSemFio> listaConexao;

    @ManyToMany
    @JoinTable(name = "notebook_entrada_saida",
               joinColumns = @JoinColumn(name = "id_notebook"),
               inverseJoinColumns = @JoinColumn(name = "id_entrada_saida"))
    private List<EntradaSaida> listaEntradaSaida;

    @Column(name = "tipo_placa_video", nullable = false)
    private TipoPlacaVideo tipoPlacaVideo;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public Boolean getIsGamer() {
        return isGamer;
    }

    public void setIsGamer(Boolean isGamer) {
        this.isGamer = isGamer;
    }

    public Integer getNumUsb() {
        return numUsb;
    }

    public void setNumUsb(Integer numUsb) {
        this.numUsb = numUsb;
    }

    public String getLimiteRam() {
        return limiteRam;
    }

    public void setLimiteRam(String limiteRam) {
        this.limiteRam = limiteRam;
    }
    
    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public List<Recurso> getListaRecurso() {
        return listaRecurso;
    }

    public void setListaRecurso(List<Recurso> listaRecurso) {
        this.listaRecurso = listaRecurso;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public PlacaVideo getPlacaVideo() {
        return placaVideo;
    }

    public void setPlacaVideo(PlacaVideo placaVideo) {
        this.placaVideo = placaVideo;
    }

    public Processador getProcessador() {
        return processador;
    }

    public void setProcessador(Processador processador) {
        this.processador = processador;
    }

    public List<Armazenamento> getListaArmazenamento() {
        return listaArmazenamento;
    }

    public void setListaArmazenamento(List<Armazenamento> listaArmazenamento) {
        this.listaArmazenamento = listaArmazenamento;
    }

    public List<MemoriaRam> getListaMemoriaRam() {
        return listaMemoriaRam;
    }

    public void setListaMemoriaRam(List<MemoriaRam> listaMemoriaRam) {
        this.listaMemoriaRam = listaMemoriaRam;
    }

    public Tela getTela() {
        return tela;
    }

    public void setTela(Tela tela) {
        this.tela = tela;
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao especificacao) {
        this.especificacao = especificacao;
    }

    public List<ConexaoSemFio> getListaConexao() {
        return listaConexao;
    }

    public void setListaConexao(List<ConexaoSemFio> listaConexao) {
        this.listaConexao = listaConexao;
    }

    public List<EntradaSaida> getListaEntradaSaida() {
        return listaEntradaSaida;
    }

    public void setListaEntradaSaida(List<EntradaSaida> listaEntradaSaida) {
        this.listaEntradaSaida = listaEntradaSaida;
    }

    public TipoPlacaVideo getTipoPlacaVideo() {
        return tipoPlacaVideo;
    }

    public void setTipoPlacaVideo(TipoPlacaVideo tipoPlacaVideo) {
        this.tipoPlacaVideo = tipoPlacaVideo;
    }
}