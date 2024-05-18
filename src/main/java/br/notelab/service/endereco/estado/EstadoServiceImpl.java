package br.notelab.service.endereco.estado;

import java.util.List;

import br.notelab.dto.endereco.estado.EstadoDTO;
import br.notelab.dto.endereco.estado.EstadoResponseDTO;
import br.notelab.model.endereco.Estado;
import br.notelab.repository.EstadoRepository;
import br.notelab.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    public EstadoRepository estadoRepository;

    @Override
    @Transactional
    public EstadoResponseDTO create(@Valid EstadoDTO dto) {
        validarNomeEstado(dto.nome());
        validarSiglaEstado(dto.sigla());

        Estado e = new Estado();
        e.setNome(dto.nome());
        e.setSigla(dto.sigla());

        estadoRepository.persist(e);
        return EstadoResponseDTO.valueOf(e);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid EstadoDTO dto) {
        validarNomeEstado(dto.nome());
        validarSiglaEstado(dto.sigla());

        Estado e = estadoRepository.findById(id);
        e.setNome(dto.nome());
        e.setSigla(dto.sigla());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public EstadoResponseDTO findById(Long id) {
        return EstadoResponseDTO.valueOf(estadoRepository.findById(id));
    }

    @Override
    public List<EstadoResponseDTO> findAll() {
        return estadoRepository.listAll().stream().map(e -> EstadoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EstadoResponseDTO> findByNome(String nome) {
        return estadoRepository.findByNome(nome).stream().map(e -> EstadoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EstadoResponseDTO> findBySigla(String sigla) {
        return estadoRepository.findBySigla(sigla).stream().map(e -> EstadoResponseDTO.valueOf(e)).toList();
    }

    private void validarNomeEstado(String nome){
        if(estadoRepository.findByNomeCompleto(nome) != null)
            throw new ValidationException("nome", "O estado "+nome+" já existe");
    }

    private void validarSiglaEstado(String sigla){
        if(estadoRepository.findBySiglaCompleta(sigla) != null)
            throw new ValidationException("sigla", "A sigla "+sigla+" já existe");
    }
}
