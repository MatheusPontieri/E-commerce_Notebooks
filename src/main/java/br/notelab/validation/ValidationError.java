package br.notelab.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends br.notelab.util.Error { // Nome completo do caminho pois já tem a classe Error por padrão (java.lang) no Java e quero a que criei.

    record FieldError(String fieldName, String message) {};

    private List<FieldError> errors = null; // Lista de Erros

    public ValidationError(String code, String message) {
        super(code, message);
    }

    public void addFieldError(String fieldName, String message) {
        if (errors == null)
            errors = new ArrayList<FieldError>();
        errors.add(new FieldError(fieldName, message));
    }

    public List<FieldError> getErrors() {
        return errors.stream().toList();
    }
  
}