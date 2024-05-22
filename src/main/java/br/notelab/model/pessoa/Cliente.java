package br.notelab.model.pessoa;

import java.util.List;

import br.notelab.model.DefaultEntity;
import br.notelab.model.notebook.Notebook;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente extends DefaultEntity {
    @Column(nullable = false)
    private Boolean aceitaMarketing;
    
    @OneToOne
    @JoinColumn(name = "id_pessoa", nullable = false, unique = true)
    private Pessoa pessoa;

    @ManyToMany
    @JoinTable(name = "lista_desejo", 
               joinColumns = @JoinColumn(name = "id_cliente"),
               inverseJoinColumns = @JoinColumn(name = "id_notebook"))
    private List<Notebook> listaDesejo;

    public Boolean getAceitaMarketing() {
        return aceitaMarketing;
    }

    public void setAceitaMarketing(Boolean aceitaMarketing) {
        this.aceitaMarketing = aceitaMarketing;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}