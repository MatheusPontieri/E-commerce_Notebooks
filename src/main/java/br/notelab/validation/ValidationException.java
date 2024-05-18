package br.notelab.validation;

public class ValidationException extends RuntimeException {

    private String fieldName;

    public ValidationException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
    
} // Diferença de ValidationException pra ValidationError -> Validation exception vai ser a exceção que acontecerá na execução. Quando acontecer (no momento que acontecer o throw new ValidationException), eu já tenho um mapper que vai
// capturar essa ValidationException e montará um ValidationError para retornar isso.
// API não guarda estado (controle de informação). Ex: Armazenar quem está logado se vou de uma página pra outra no Educa. Back não valida isso toda hora. 
// Carrinho de compra é no front-end
// Back pra quando a compra for realizada
// Modelagem (pensar no cadastro da informação (tela)) bem feita tem menos código pra dar manutenção