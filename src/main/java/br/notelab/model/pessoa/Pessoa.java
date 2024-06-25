package br.notelab.model.pessoa;

import java.time.LocalDate;
import java.util.List;

import br.notelab.model.DefaultEntity;
import br.notelab.model.endereco.Endereco;
import br.notelab.model.file.FileInfo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Pessoa extends DefaultEntity {
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true)
    private String cpf;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "telefone_pessoa",
               joinColumns = @JoinColumn(name = "id_pessoa"),
               inverseJoinColumns = @JoinColumn(name = "id_telefone", unique = true))
    private List<Telefone> listaTelefone;

    @Column(nullable = false)
    private Sexo sexo;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_pessoa", nullable = false)
    private List<Endereco> listaEndereco;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "pessoa_imagem",
               joinColumns = @JoinColumn(name = "id_pessoa"),
               inverseJoinColumns = @JoinColumn(name = "id_imagem", unique = true))
    private List<FileInfo> listaNomeImagem;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Telefone> getListaTelefone() {
        return listaTelefone;
    }

    public void setListaTelefone(List<Telefone> listaTelefone) {
        this.listaTelefone = listaTelefone;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Endereco> getListaEndereco() {
        return listaEndereco;
    }

    public void setListaEndereco(List<Endereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }

    public List<FileInfo> getListaNomeImagem() {
        return listaNomeImagem;
    }

    public void setListaNomeImagem(List<FileInfo> listaImagem) {
        this.listaNomeImagem = listaImagem;
    }
}