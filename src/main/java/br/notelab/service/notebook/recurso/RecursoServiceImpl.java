package br.notelab.service.notebook.recurso;

import java.util.List;

import br.notelab.dto.notebook.recurso.RecursoDTO;
import br.notelab.dto.notebook.recurso.RecursoResponseDTO;
import br.notelab.model.notebook.Recurso;
import br.notelab.repository.RecursoRepository;
import br.notelab.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class RecursoServiceImpl implements RecursoService {

    @Inject
    public RecursoRepository recursoRepository;

    @Override
    @Transactional
    public RecursoResponseDTO create(@Valid RecursoDTO dto) {
        validarNomeRecurso(dto.nome());

        Recurso r = new Recurso();
        r.setNome(dto.nome());

        recursoRepository.persist(r);
        return RecursoResponseDTO.valueOf(r);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid RecursoDTO dto) {
        validarNomeRecurso(dto.nome());

        Recurso r = recursoRepository.findById(id);
        r.setNome(dto.nome());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        recursoRepository.deleteById(id);
    }

    @Override
    public RecursoResponseDTO findById(Long id) {
        return RecursoResponseDTO.valueOf(recursoRepository.findById(id));
    }

    @Override
    public List<RecursoResponseDTO> findAll() {
        return recursoRepository.listAll().stream().map(p -> RecursoResponseDTO.valueOf(p)).toList();
    }

    @Override
    public List<RecursoResponseDTO> findByNome(String nome) {
        return recursoRepository.findByNome(nome).stream().map(p -> RecursoResponseDTO.valueOf(p)).toList();
    }

    private void validarNomeRecurso(String nome){
        if(recursoRepository.findByNomeCompleto(nome) != null)
            throw new ValidationException("nome", "O recurso "+nome+" já existe");
    }
}
