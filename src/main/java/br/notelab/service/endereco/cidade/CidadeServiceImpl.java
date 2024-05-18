package br.notelab.service.endereco.cidade;

import java.util.List;

import br.notelab.dto.endereco.cidade.CidadeDTO;
import br.notelab.dto.endereco.cidade.CidadeResponseDTO;
import br.notelab.model.endereco.Cidade;
import br.notelab.repository.CidadeRepository;
import br.notelab.repository.EstadoRepository;
import br.notelab.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {

    @Inject
    public CidadeRepository cidadeRepository;

    @Inject
    public EstadoRepository estadoRepository;

    @Override
    @Transactional
    public CidadeResponseDTO create(@Valid CidadeDTO dto) {
        validarAtributosCidade(dto.nome(), dto.idEstado());

        Cidade c = new Cidade();
        c.setNome(dto.nome());
        c.setEstado(estadoRepository.findById(dto.idEstado()));

        cidadeRepository.persist(c);
        return CidadeResponseDTO.valueOf(c);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid CidadeDTO dto) {
        validarAtributosCidade(dto.nome(), dto.idEstado());

        Cidade c = cidadeRepository.findById(id);
        c.setNome(dto.nome());
        c.setEstado(estadoRepository.findById(dto.idEstado()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cidadeRepository.deleteById(id);
    }

    @Override
    public CidadeResponseDTO findById(Long id) {
        return CidadeResponseDTO.valueOf(cidadeRepository.findById(id));
    }

    @Override
    public List<CidadeResponseDTO> findAll() {
        return cidadeRepository.listAll().stream().map(c -> CidadeResponseDTO.valueOf(c)).toList();
    }

    @Override
    public List<CidadeResponseDTO> findByNome(String nome) {
        return cidadeRepository.findByNome(nome).stream().map(c -> CidadeResponseDTO.valueOf(c)).toList();
    }

    @Override
    public List<CidadeResponseDTO> findByEstado(String nomeEstado) {
        return cidadeRepository.findByEstado(nomeEstado).stream().map(CidadeResponseDTO::valueOf).toList();
    }

    private void validarAtributosCidade(String nome, Long idEstado){
        if (cidadeRepository.findByAllAtributtes(nome, idEstado) != null)
            throw new ValidationException( "nome", "A cidade "+nome+" já existe nesse estado!");
    }
}