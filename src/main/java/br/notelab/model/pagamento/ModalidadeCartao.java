package br.notelab.model.pagamento;

public enum ModalidadeCartao {
    DEBITO (1, "Débito"),
    CREDITO (2, "Crédito");

    private final int id;
    private final String descricao;

    ModalidadeCartao(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static ModalidadeCartao valueOf(int id){
        for (ModalidadeCartao modalidade : ModalidadeCartao.values()) {
            if(modalidade.getId() == id)
                return modalidade;
        }
        throw new IllegalArgumentException("Id Sexo Inválido!");
    }
    
}