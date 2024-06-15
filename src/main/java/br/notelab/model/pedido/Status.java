package br.notelab.model.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    AGUARDANDO_PAGAMENTO(1, "Aguardando confirmação do pagamento"),
    PAGAMENTO_EXPIRADO(2, "Pagamento expirado"),
    PAGAMENTO_CONFIRMADO(3, "Pagamento confirmado"),
    SEPARADO_DO_ESTOQUE(4, "Separado do estoque"),
    ENTREGUE_A_TRANSPORTADORA(5, "Entregue para a transportadora"),
    ENTREGUE(6, "Entregue"),
    DESISTIDO(7, "Desistiu da compra antes da entrega para a transportadora"),
    DEVOLVIDO(8, "Devolveu a compra após a entrega");
    
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