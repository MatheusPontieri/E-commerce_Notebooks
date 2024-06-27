package br.notelab.model.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    AGUARDANDO_PAGAMENTO(1, "Aguardando confirmação do pagamento"),
    PAGAMENTO_EXPIRADO(2, "Pagamento expirado"),
    PAGAMENTO_CONFIRMADO(3, "Pagamento confirmado"),
    EM_TRANSITO(4, "Pedido em trânsito"),
    ENTREGUE(5, "Pedido entregue"),
    DESISTIDO(6, "Desistência do pedido antes do envio"),
    DEVOLVIDO(7, "Devolução do pedido após a entrega");
    
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