package br.notelab.model.pessoa.funcionario;

public enum Perfil {
    FUNCIONARIO (1, "Funcionario"),
    ADMINISTRADOR (2, "Administrador");

    private final int id;
    private final String descricao;

    Perfil(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil valueOf(int id){
        for(Perfil perfil : Perfil.values()){
            if(perfil.getId() == id)
                return perfil;
        }
        
        throw new IllegalArgumentException("Id Perfil Inválido!");
    }
}