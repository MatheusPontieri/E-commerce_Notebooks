package br.notelab.model.pessoa;

import br.notelab.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente extends DefaultEntity {
    @Column(nullable = false)
    private Boolean aceitaMarketing;
    
    @OneToOne
    @JoinColumn(name = "id_pessoa", nullable = false, unique = true)
    private Pessoa pessoa;

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