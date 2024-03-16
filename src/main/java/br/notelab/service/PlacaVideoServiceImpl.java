package br.notelab.service;

import java.util.List;

import br.notelab.dto.notebook.PlacaVideoDTO;
import br.notelab.dto.notebook.PlacaVideoResponseDTO;
import br.notelab.model.notebook.PlacaVideo;
import br.notelab.repository.PlacaVideoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class PlacaVideoServiceImpl implements PlacaVideoService{

    @Inject
    public PlacaVideoRepository placaVideoRepository;

    @Override
    @Transactional
    public PlacaVideoResponseDTO create(@Valid PlacaVideoDTO dto) {
        PlacaVideo placaVideo = new PlacaVideo();

        placaVideo.setModelo(dto.modelo());
        placaVideo.setMemoriaVideo(dto.memoriaVideo());

        placaVideoRepository.persist(placaVideo);
        return PlacaVideoResponseDTO.valueOf(placaVideo);
    }

    @Override
    @Transactional
    public void update(Long id, PlacaVideoDTO dto) {
        PlacaVideo placaVideo = placaVideoRepository.findById(id);

        placaVideo.setModelo(dto.modelo());
        placaVideo.setMemoriaVideo(dto.memoriaVideo());
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
        return placaVideoRepository.findAll().stream().map(p -> PlacaVideoResponseDTO.valueOf(p)).toList();
    }

    @Override
    public List<PlacaVideoResponseDTO> findByModelo(String modelo) {
        return placaVideoRepository.findByModelo(modelo).stream().map(p -> PlacaVideoResponseDTO.valueOf(p)).toList();
    }
    
}
