package br.notelab.service;

import java.util.List;

import br.notelab.dto.notebook.ProcessadorDTO;
import br.notelab.dto.notebook.ProcessadorResponseDTO;
import br.notelab.model.notebook.Processador;
import br.notelab.repository.ProcessadorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ProcessadorServiceImpl implements ProcessadorService {

    @Inject
    public ProcessadorRepository processadorRepository;

    @Override
    @Transactional
    public ProcessadorResponseDTO create(@Valid ProcessadorDTO dto) {
        Processador p = new Processador();

        p.setModelo(dto.modelo());
        p.setGeracao(dto.geracao());
        p.setVelocidade(dto.velocidade());
        p.setNucleos(dto.nucleos());
        p.setThreads(dto.threads());
        p.setMemoriaCache(dto.memoriaCache());
        
        processadorRepository.persist(p);
        return ProcessadorResponseDTO.valueOf(p);
    }

    @Override
    @Transactional
    public void update(Long id, ProcessadorDTO dto) {
        Processador p = processadorRepository.findById(id);
        
        p.setModelo(dto.modelo());
        p.setGeracao(dto.geracao());
        p.setVelocidade(dto.velocidade());
        p.setNucleos(dto.nucleos());
        p.setThreads(dto.threads());
        p.setMemoriaCache(dto.memoriaCache());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        processadorRepository.deleteById(id);
    }

    @Override
    public ProcessadorResponseDTO findById(Long id) {
        return ProcessadorResponseDTO.valueOf(processadorRepository.findById(id));
    }

    @Override
    public List<ProcessadorResponseDTO> findAll() {
        return processadorRepository.listAll().stream().map(p -> ProcessadorResponseDTO.valueOf(p)).toList();
    }

    @Override
    public List<ProcessadorResponseDTO> findByModelo(String modelo) {
        return processadorRepository.findByModelo(modelo).stream().map(p -> ProcessadorResponseDTO.valueOf(p)).toList();

    }

    @Override
    public List<ProcessadorResponseDTO> findByGeracao(String geracao) {
        return processadorRepository.findByGeracao(geracao).stream().map(p -> ProcessadorResponseDTO.valueOf(p)).toList();
    }
    
}
