package br.notelab.model.converterjpa;

import br.notelab.model.notebook.gpu.TipoPlacaVideo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoPlacaVideoConverter implements AttributeConverter<TipoPlacaVideo, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoPlacaVideo tipoPlacaVideo) {
        return tipoPlacaVideo.getId();
    }

    @Override
    public TipoPlacaVideo convertToEntityAttribute(Integer id) {
        return TipoPlacaVideo.valueOf(id);
    }
    
}
