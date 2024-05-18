package br.notelab.service.notebook.gpu;

import java.util.List;

import br.notelab.dto.notebook.gpu.PlacaVideoDTO;
import br.notelab.dto.notebook.gpu.PlacaVideoResponseDTO;
import br.notelab.model.notebook.gpu.PlacaVideo;
import br.notelab.repository.PlacaVideoRepository;
import br.notelab.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class PlacaVideoServiceImpl implements PlacaVideoService {

    @Inject
    public PlacaVideoRepository placaVideoRepository;

    @Override
    @Transactional
    public PlacaVideoResponseDTO create(@Valid PlacaVideoDTO dto) {
        validarAtributosPlacaVideo(dto.modelo(), dto.memoriaVideo());

        PlacaVideo p = new PlacaVideo();
        p.setModelo(dto.modelo());
        p.setMemoriaVideo(dto.memoriaVideo());

        placaVideoRepository.persist(p);
        return PlacaVideoResponseDTO.valueOf(p);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid PlacaVideoDTO dto) {
        validarAtributosPlacaVideo(dto.modelo(), dto.memoriaVideo());

        PlacaVideo p = placaVideoRepository.findById(id);
        p.setModelo(dto.modelo());
        p.setMemoriaVideo(dto.memoriaVideo());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        placaVideoRepository.deleteById(id);
    }

    @Override
    public PlacaVideoResponseDTO findById(Long id) {
        return PlacaVideoResponseDTO.valueOf(placaVideoRepository.findById(id));
    }

    @Override
    public List<PlacaVideoResponseDTO> findAll() {
        return placaVideoRepository.listAll().stream().map(p -> PlacaVideoResponseDTO.valueOf(p)).toList();
    }

    @Override
    public List<PlacaVideoResponseDTO> findByModelo(String modelo) {
        return placaVideoRepository.findByModelo(modelo).stream().map(p -> PlacaVideoResponseDTO.valueOf(p)).toList();
    }

    @Override
    public List<PlacaVideoResponseDTO> findByMemoriaVideo(String memoria) {
        return placaVideoRepository.findByMemoriaVideo(memoria).stream().map(p -> PlacaVideoResponseDTO.valueOf(p)).toList();

    }
    
    private void validarAtributosPlacaVideo(String modelo, String memoria){
        if(placaVideoRepository.findByAllAttributes(modelo, memoria) != null)
            throw new ValidationException("modelo e memoria", "O modelo "+modelo+" com a memória "+memoria+" já existem");
    }
}
