package br.notelab.service.notebook.memoria;

import java.util.List;

import br.notelab.dto.notebook.memoria.ArmazenamentoDTO;
import br.notelab.dto.notebook.memoria.ArmazenamentoResponseDTO;
import br.notelab.model.notebook.memoria.Armazenamento;
import br.notelab.repository.ArmazenamentoRepository;
import br.notelab.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ArmazenamentoServiceImpl implements ArmazenamentoService {

    @Inject
    public ArmazenamentoRepository armazenamentoRepository;

    @Override
    @Transactional
    public ArmazenamentoResponseDTO create(@Valid ArmazenamentoDTO dto) {
        validarAtributosArmazenamento(dto.nome(), dto.capacidade());

        Armazenamento a = new Armazenamento();
        a.setNome(dto.nome());
        a.setCapacidade(dto.capacidade());

        armazenamentoRepository.persist(a);
        return ArmazenamentoResponseDTO.valueOf(a);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid ArmazenamentoDTO dto) {
        validarAtributosArmazenamento(dto.nome(), dto.capacidade());

        Armazenamento a = armazenamentoRepository.findById(id);
        a.setNome(dto.nome());
        a.setCapacidade(dto.capacidade());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        armazenamentoRepository.deleteById(id);
    }

    @Override
    public ArmazenamentoResponseDTO findById(Long id) {
        return ArmazenamentoResponseDTO.valueOf(armazenamentoRepository.findById(id));
    }

    @Override
    public List<ArmazenamentoResponseDTO> findAll() {
        return armazenamentoRepository.listAll().stream().map(p -> ArmazenamentoResponseDTO.valueOf(p)).toList();
    }

    @Override
    public List<ArmazenamentoResponseDTO> findByNome(String nome) {
        return armazenamentoRepository.findByNome(nome).stream().map(p -> ArmazenamentoResponseDTO.valueOf(p)).toList();
    }

    @Override
    public List<ArmazenamentoResponseDTO> findByCapacidade(String capacidade) {
        return armazenamentoRepository.findByCapacidade(capacidade).stream().map(p -> ArmazenamentoResponseDTO.valueOf(p)).toList();
    }

    private void validarAtributosArmazenamento(String nome, String capacidade){
        if (armazenamentoRepository.findByAllAttributes(nome, capacidade) != null) 
            throw new ValidationException("nome e capacidade", "O nome "+nome+" com a capacidade "+capacidade+" já existem");
    }
}