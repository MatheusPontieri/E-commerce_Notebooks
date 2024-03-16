package br.notelab.service;

import java.util.List;

import br.notelab.dto.notebook.MemoriaRamDTO;
import br.notelab.dto.notebook.MemoriaRamResponseDTO;
import br.notelab.dto.notebook.ProcessadorResponseDTO;
import br.notelab.model.notebook.MemoriaRam;
import br.notelab.repository.MemoriaRamRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class MemoriaRamServiceImpl implements MemoriaRamService {

    @Inject
    public MemoriaRamRepository memoriaRamRepository;

    @Override
    @Transactional
    public MemoriaRamResponseDTO create(@Valid MemoriaRamDTO dto) {
        MemoriaRam memoria = new MemoriaRam();

        memoria.setCapacidade(dto.capacidade());
        memoria.setExpansivelAte(dto.expansivelAte());

        memoriaRamRepository.persist(memoria);
        return MemoriaRamResponseDTO.valueOf(memoria);
    }

    @Override
    @Transactional
    public void update(Long id, MemoriaRamDTO dto) {
        MemoriaRam memoria = memoriaRamRepository.findById(id);

        memoria.setCapacidade(dto.capacidade());
        memoria.setExpansivelAte(dto.expansivelAte());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        memoriaRamRepository.deleteById(id);
    }

    @Override
    public MemoriaRamResponseDTO findById(Long id) {
        return MemoriaRamResponseDTO.valueOf(memoriaRamRepository.findById(id));

    }

    @Override
    public List<MemoriaRamResponseDTO> findAll() {
        return memoriaRamRepository.listAll().stream().map(m -> MemoriaRamResponseDTO.valueOf(m)).toList();
    }

    @Override
    public List<MemoriaRamResponseDTO> findByCapacidade(String capacidade) {
        return memoriaRamRepository.findByCapacidade(capacidade).stream().map(m -> MemoriaRamResponseDTO.valueOf(m)).toList();
    }
}