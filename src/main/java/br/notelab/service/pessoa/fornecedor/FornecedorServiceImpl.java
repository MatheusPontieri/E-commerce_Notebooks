package br.notelab.service.pessoa.fornecedor;

import java.util.List;

import br.notelab.dto.pessoa.fornecedor.FornecedorDTO;
import br.notelab.dto.pessoa.fornecedor.FornecedorResponseDTO;
import br.notelab.dto.pessoa.telefone.TelefoneDTO;
import br.notelab.model.pessoa.Fornecedor;
import br.notelab.model.pessoa.Telefone;
import br.notelab.repository.FornecedorRepository;
import br.notelab.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    public FornecedorRepository fornecedorRepository;

    @Override
    @Transactional
    public FornecedorResponseDTO create(@Valid FornecedorDTO dto) {
        validarEmailFornecedor(dto.email(), 0L);
        validarCnpjFornecedor(dto.cnpj(), 0L);
        validarListaTelefoneFornecedor(dto.telefones(), 0L);

        Fornecedor f = new Fornecedor();
        f.setNome(dto.nome());
        f.setEmail(dto.email());
        f.setCnpj(dto.cnpj());
        f.setListaTelefone(dto.telefones().stream().map(TelefoneDTO::convertToTelefone).toList());

        fornecedorRepository.persist(f);
        return FornecedorResponseDTO.valueOf(f);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid FornecedorDTO dto) {
        Fornecedor f = fornecedorRepository.findById(id);

        validarEmailFornecedor(dto.email(), f.getId());
        validarCnpjFornecedor(dto.cnpj(), f.getId());
        validarListaTelefoneFornecedor(dto.telefones(), f.getId());

        f.setNome(dto.nome());
        f.setEmail(dto.email());
        f.setCnpj(dto.cnpj());

        f.getListaTelefone().clear();
        dto.telefones().forEach(t -> f.getListaTelefone().add(TelefoneDTO.convertToTelefone(t)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }

    @Override
    public FornecedorResponseDTO findById(Long id) {
        return FornecedorResponseDTO.valueOf(fornecedorRepository.findById(id));
    }

    @Override
    public List<FornecedorResponseDTO> findAll() {
        return fornecedorRepository.listAll().stream().map(f -> FornecedorResponseDTO.valueOf(f)).toList();
    }

    @Override
    public List<FornecedorResponseDTO> findByNome(String nome) {
        return fornecedorRepository.findByNome(nome).stream().map(f -> FornecedorResponseDTO.valueOf(f)).toList();
    }

    private void validarEmailFornecedor(String email, Long id){
        if(fornecedorRepository.findByEmailCompleto(email, id) != null)
            throw new ValidationException("email", "O email "+email+" já existe");
    }

    private void validarCnpjFornecedor(String cnpj, Long id){
        if(fornecedorRepository.findByCnpjCompleto(cnpj, id) != null)
            throw new ValidationException("cnpj", "O cnpj "+cnpj+" já existe");
    }

    private void validarListaTelefoneFornecedor(List<TelefoneDTO> lista, Long id){
        for (TelefoneDTO telefone : lista) {
            if(fornecedorRepository.findByTelefoneCompleto(telefone.codigoArea(), telefone.numero(), id) != null)
                throw new ValidationException("telefone", "O telefone "+ telefone.codigoArea().concat(telefone.numero()) +" já existe");
        }
    }
}