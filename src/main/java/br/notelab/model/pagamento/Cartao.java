package br.notelab.model.pagamento;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Cartao extends Pagamento {

    private Double valor;

    public static void main(String[] args) {
       Cartao c = new Cartao();
    }
}