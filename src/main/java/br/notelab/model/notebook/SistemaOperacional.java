package br.notelab.model.notebook;

public enum SistemaOperacional {
    WINDOWS (1, "Windows"),
    LINUX (2, "Linux"),
    MAC_OS (3, "Mac OS");

    private final int id;
    private final String descricao;

    private SistemaOperacional(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public static SistemaOperacional valueOf(int opcao){
        for (SistemaOperacional sistema : SistemaOperacional.values()){
            if (sistema.id == opcao)
                return sistema;
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
