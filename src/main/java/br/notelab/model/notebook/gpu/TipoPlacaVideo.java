package br.notelab.model.notebook.gpu;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoPlacaVideo {
    INTEGRADA (1, "Integrada (On-Board)"),
    DEDICADA (2, "Dedicada (Off-Board)");

    private final int id;
    private final String descricao;

    TipoPlacaVideo(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoPlacaVideo valueOf(Integer id) throws IllegalArgumentException {
        for (TipoPlacaVideo tipo : TipoPlacaVideo.values()){
            if (tipo.id == id)
                return tipo;
        }
        throw new IllegalArgumentException("Id Inválido do Tipo da Placa");
    }
}