package br.notelab.service.notebook.memoria;

import java.util.List;

import br.notelab.dto.notebook.memoria.MemoriaRamDTO;
import br.notelab.dto.notebook.memoria.MemoriaRamResponseDTO;
import br.notelab.model.notebook.memoria.MemoriaRam;
import br.notelab.repository.MemoriaRamRepository;
import br.notelab.validation.ValidationException;
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
        validarAtributosMemoria(dto.capacidade(), dto.limiteExpansao());

        MemoriaRam m = new MemoriaRam();
        m.setCapacidade(dto.capacidade());
        m.setLimiteExpansao(dto.limiteExpansao());
        
        memoriaRamRepository.persist(m);
        return MemoriaRamResponseDTO.valueOf(m);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid MemoriaRamDTO dto) {
        validarAtributosMemoria(dto.capacidade(), dto.limiteExpansao());

        MemoriaRam p = memoriaRamRepository.findById(id);
        p.setLimiteExpansao(dto.limiteExpansao());
        p.setCapacidade(dto.capacidade());
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
        return memoriaRamRepository.listAll().stream().map(p -> MemoriaRamResponseDTO.valueOf(p)).toList();
    }

    @Override
    public List<MemoriaRamResponseDTO> findByCapacidade(String capacidade) {
        return memoriaRamRepository.findByCapacidade(capacidade).stream().map(p -> MemoriaRamResponseDTO.valueOf(p)).toList();
    }

    private void validarAtributosMemoria(String capacidade, String limite){
        if(memoriaRamRepository.findByAllAttributes(capacidade, limite) != null)
            throw new ValidationException("capacidade e limite de expansão",
                                         "A capacidade "+capacidade+" com o limite de expansão "+limite+" já existem");
    }
}