package br.notelab.service.notebook.processador;

import java.util.List;

import br.notelab.dto.notebook.processador.ProcessadorDTO;
import br.notelab.dto.notebook.processador.ProcessadorResponseDTO;
import br.notelab.model.notebook.Processador;
import br.notelab.repository.ProcessadorRepository;
import br.notelab.validation.ValidationException;
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
        validarModeloProcessador(dto.modelo());

        Processador p = new Processador();
        p.setModelo(dto.modelo());
        p.setVelocidade(dto.velocidade());
        p.setMemoriaCache(dto.memoriaCache());

        processadorRepository.persist(p);
        return ProcessadorResponseDTO.valueOf(p);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid ProcessadorDTO dto) {
        validarModeloProcessador(dto.modelo());

        Processador p = processadorRepository.findById(id);
        p.setModelo(dto.modelo());
        p.setVelocidade(dto.velocidade());
        p.setMemoriaCache(dto.memoriaCache());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        processadorRepository.deleteById(id);
    }

    @Override
    @Transactional
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

    private void validarModeloProcessador(String modelo){
        if(processadorRepository.findByModeloCompleto(modelo) != null)
            throw new ValidationException("modelo", "O modelo "+modelo+" já existe");
    }
}
