package br.notelab.model.converterjpa;

import br.notelab.model.pedido.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status attribute) {
        return attribute.getId();
    }

    @Override
    public Status convertToEntityAttribute(Integer id) {
        return Status.valueOf(id);
    }
}