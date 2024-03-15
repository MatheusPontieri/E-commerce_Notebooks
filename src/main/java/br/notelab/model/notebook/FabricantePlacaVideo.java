package br.notelab.model.notebook;

public enum FabricantePlacaVideo {
    NVIDIA (1, "Nvidia"),
    AMD (2, "AMD");

    private final int id;
    private final String descricao;

    private FabricantePlacaVideo(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public static FabricantePlacaVideo valueOf(int opcao){
        for (FabricantePlacaVideo fabricante : FabricantePlacaVideo.values()){
            if (fabricante.id == opcao)
                return fabricante;
        }
        return null; 
    }
}
