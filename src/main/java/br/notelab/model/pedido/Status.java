package br.notelab.model.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    AGUARDANDO_PAGAMENTO(0, "Aguardando confirmação do pagamento"),
    PAGAMENTO_NÃO_AUTORIZADO(1, "Pagamento não autorizado pela financeira"),
    PAGAMENTO_AUTORIZADO(2, "Pagamento recebido"),
    SEPARADO_DO_ESTOQUE(3, "Separado do estoque"),
    ENTREGUE_A_TRANSPORTADORA(4, "Entregue para a transportadora"),
    ENTREGUE(5, "Entregue"),
    DESISTIDO(6, "Desistiu da compra antes da entrega para a transportadora"),
    DEVOLVIDO(7, "Devolveu a compra após a entrega");
    
    private int id;
    private String descricao;

    Status(int id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status valueOf(int id) throws IllegalArgumentException{
        for (Status status : Status.values()){
            if (status.getId() == id)
                return status;
        }

        throw new IllegalArgumentException("Id Sexo Inválido!");
    }
}