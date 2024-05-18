package br.notelab.service.notebook.conexao;

import java.util.List;

import br.notelab.dto.notebook.conexao.EntradaSaidaDTO;
import br.notelab.dto.notebook.conexao.EntradaSaidaResponseDTO;
import br.notelab.model.notebook.conexao.EntradaSaida;
import br.notelab.repository.EntradaSaidaRepository;
import br.notelab.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class EntradaSaidaServiceImpl implements EntradaSaidaService {

    @Inject
    public EntradaSaidaRepository entradaSaidaRepository;

    @Override
    @Transactional
    public EntradaSaidaResponseDTO create(@Valid EntradaSaidaDTO dto) {
        validarNomeEntradaSaida(dto.nome());

        EntradaSaida e = new EntradaSaida();
        e.setNome(dto.nome());

        entradaSaidaRepository.persist(e);
        return EntradaSaidaResponseDTO.valueOf(e);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid EntradaSaidaDTO dto) {
        validarNomeEntradaSaida(dto.nome());

        EntradaSaida e = entradaSaidaRepository.findById(id);
        e.setNome(dto.nome());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        entradaSaidaRepository.deleteById(id);
    }

    @Override
    public EntradaSaidaResponseDTO findById(Long id) {
        return EntradaSaidaResponseDTO.valueOf(entradaSaidaRepository.findById(id));
    }

    @Override
    public List<EntradaSaidaResponseDTO> findAll() {
        return entradaSaidaRepository.listAll().stream().map(e -> EntradaSaidaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EntradaSaidaResponseDTO> findByNome(String nome) {
        return entradaSaidaRepository.findByNome(nome).stream().map(e -> EntradaSaidaResponseDTO.valueOf(e)).toList();
    }

    private void validarNomeEntradaSaida(String nome){
        if(entradaSaidaRepository.findByNomeCompleto(nome) != null)
            throw new ValidationException("nome", "A entrada ou saída "+nome+" já existe");
    }
}
