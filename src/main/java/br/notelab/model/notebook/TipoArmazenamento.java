package br.notelab.model.notebook;

public enum TipoArmazenamento {
    HD (1, "HD"),
    SSD (2, "SSD");

    private final int id;
    private final String descricao;

    private TipoArmazenamento(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public static TipoArmazenamento valueOf(int opcao){
        for (TipoArmazenamento tipo : TipoArmazenamento.values()){
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
