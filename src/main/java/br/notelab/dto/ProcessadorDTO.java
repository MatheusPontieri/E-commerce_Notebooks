package br.notelab.dto;

public record ProcessadorDTO(
    String modelo,
    String geracao,
    String velocidade,
    int nucleos,
    int threads,
    String memoriaCache
) {
    
}