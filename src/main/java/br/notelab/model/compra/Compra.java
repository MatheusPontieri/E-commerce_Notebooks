package br.notelab.model.compra;

import java.time.LocalDate;
import java.util.List;

import br.notelab.model.Endereco;
import br.notelab.model.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate data;

    @ManyToOne
    private Usuario usuario;
    
    @OneToMany
    private List<ItemCompra> itens;

    @ManyToOne
    private Endereco endereco;
}
