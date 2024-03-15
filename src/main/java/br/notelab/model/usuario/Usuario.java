package br.notelab.model.usuario;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Usuario {
    private Long id;

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String senha;
}