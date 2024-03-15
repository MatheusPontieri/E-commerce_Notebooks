package br.notelab.model.notebook;

public enum TipoPlacaVideo {
    INTEGRADA (1, "Integrada"),
    DEDICADA (1, "Dedicada");

    private final int id;
    private final String descricao;

    private TipoPlacaVideo(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public static TipoPlacaVideo valueOf(int opcao){
        for (TipoPlacaVideo tipo : TipoPlacaVideo.values()){
            if (tipo.id == opcao)
                return tipo;
        }
        return null; 
    }

    public int getId(){
        return this.id;
    }

    public String getDescricao(){
        return this.descricao;
    }
}
