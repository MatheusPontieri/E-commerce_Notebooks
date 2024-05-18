package br.notelab.service.notebook.conexao;

import java.util.List;

import br.notelab.dto.notebook.conexao.ConexaoSemFioDTO;
import br.notelab.dto.notebook.conexao.ConexaoSemFioResponseDTO;
import br.notelab.model.notebook.conexao.ConexaoSemFio;
import br.notelab.repository.ConexaoSemFioRepository;
import br.notelab.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ConexaoSemFioServiceImpl implements ConexaoSemFioService {

    @Inject
    public ConexaoSemFioRepository conexaoSemFioRepository;

    @Override
    @Transactional
    public ConexaoSemFioResponseDTO create(@Valid ConexaoSemFioDTO dto) {
        validarNomeConexaoSemFio(dto.nome());

        ConexaoSemFio p = new ConexaoSemFio();
        p.setNome(dto.nome());

        conexaoSemFioRepository.persist(p);
        return ConexaoSemFioResponseDTO.valueOf(p);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid ConexaoSemFioDTO dto) {
        validarNomeConexaoSemFio(dto.nome());

        ConexaoSemFio p = conexaoSemFioRepository.findById(id);
        p.setNome(dto.nome());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        conexaoSemFioRepository.deleteById(id);
    }

    @Override
    public ConexaoSemFioResponseDTO findById(Long id) {
        return ConexaoSemFioResponseDTO.valueOf(conexaoSemFioRepository.findById(id));
    }

    @Override
    public List<ConexaoSemFioResponseDTO> findAll() {
        return conexaoSemFioRepository.listAll().stream().map(c -> ConexaoSemFioResponseDTO.valueOf(c)).toList();
    }

    @Override
    public List<ConexaoSemFioResponseDTO> findByNome(String nome) {
        return conexaoSemFioRepository.findByNome(nome).stream().map(c -> ConexaoSemFioResponseDTO.valueOf(c)).toList();
    }
    
    private void validarNomeConexaoSemFio(String nome){
        if(conexaoSemFioRepository.findByNomeCompleto(nome) != null)
            throw new ValidationException("nome", "A conexão sem fio "+nome+" já existe");
    }
}
