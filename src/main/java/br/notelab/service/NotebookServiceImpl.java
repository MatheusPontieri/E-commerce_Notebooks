package br.notelab.service;

import java.util.List;

import br.notelab.dto.notebook.NotebookDTO;
import br.notelab.dto.notebook.NotebookResponseDTO;
import br.notelab.model.notebook.Notebook;
import br.notelab.repository.MemoriaRamRepository;
import br.notelab.repository.NotebookRepository;
import br.notelab.repository.PlacaVideoRepository;
import br.notelab.repository.ProcessadorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class NotebookServiceImpl implements NotebookService {

    @Inject
    public NotebookRepository notebookRepository;

    @Inject
    public ProcessadorRepository processadorRepository;

    @Inject
    public PlacaVideoRepository placaVideoRepository;

    @Inject
    public MemoriaRamRepository memoriaRamRepository;

    @Override
    @Transactional
    public NotebookResponseDTO create(@Valid NotebookDTO dto) {
        Notebook note = new Notebook();
        
        note.setDescricao(dto.descricao());
        note.setModelo(dto.modelo());
        note.setPreco(dto.preco());
        note.setIsGamer(dto.isGamer());
        note.setProcessador(processadorRepository.findById(dto.idProcessador()));
        note.setPlacaVideo(placaVideoRepository.findById(dto.idPlacaVideo()));
        note.setMemoriaRam(memoriaRamRepository.findById(dto.idMemoriaRam()));

        notebookRepository.persist(note);
        return NotebookResponseDTO.valueOf(note);
    }

    @Override
    @Transactional
    public void update(Long id, NotebookDTO dto) {
        Notebook note = notebookRepository.findById(id);
        
        note.setDescricao(dto.descricao());
        note.setModelo(dto.modelo());
        note.setPreco(dto.preco());
        note.setIsGamer(dto.isGamer());
        note.setProcessador(processadorRepository.findById(dto.idProcessador()));
        note.setPlacaVideo(placaVideoRepository.findById(dto.idPlacaVideo()));
        note.setMemoriaRam(memoriaRamRepository.findById(dto.idMemoriaRam()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        notebookRepository.deleteById(id);
    }

    @Override
    public NotebookResponseDTO findById(Long id) {
        return NotebookResponseDTO.valueOf(notebookRepository.findById(id));
    }

    @Override
    public List<NotebookResponseDTO> findAll() {
        return notebookRepository.findAll().stream().map(n -> NotebookResponseDTO.valueOf(n)).toList();
    }

    @Override
    public List<NotebookResponseDTO> findByDescricao(String descricao) {
        return notebookRepository.findByDescricao(descricao).stream().map(n -> NotebookResponseDTO.valueOf(n)).toList();
    }
    
}
