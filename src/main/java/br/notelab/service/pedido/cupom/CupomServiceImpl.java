package br.notelab.service.pedido.cupom;

import java.util.List;

import br.notelab.dto.pedido.cupom.CupomDTO;
import br.notelab.dto.pedido.cupom.CupomResponseDTO;
import br.notelab.model.pedido.Cupom;
import br.notelab.repository.CupomRepository;
import br.notelab.repository.FornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class CupomServiceImpl implements CupomService {

    @Inject
    public CupomRepository cupomRepository;

    @Inject
    public FornecedorRepository fornecedorRepository;

    @Override
    @Transactional
    public CupomResponseDTO create(@Valid CupomDTO dto) {
        Cupom c = new Cupom();

        c.setCodigo(dto.codigo());
        c.setPercentualDesconto(dto.percentualDesconto());
        c.setDataValidade(dto.validade());
        c.setFornecedor(fornecedorRepository.findById(dto.idFornecedor()));

        cupomRepository.persist(c);

        return CupomResponseDTO.valueOf(c);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid CupomDTO dto) {
        Cupom c = cupomRepository.findById(id);

        c.setCodigo(dto.codigo());
        c.setPercentualDesconto(dto.percentualDesconto());
        c.setDataValidade(dto.validade());
        c.setFornecedor(fornecedorRepository.findById(dto.idFornecedor()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cupomRepository.deleteById(id);
    }

    @Override
    public CupomResponseDTO findById(Long id) {
        return CupomResponseDTO.valueOf(cupomRepository.findById(id));
    }

    @Override
    public List<CupomResponseDTO> findAll() {
        return cupomRepository.listAll().stream().map(CupomResponseDTO::valueOf).toList();
    }

    @Override
    public List<CupomResponseDTO> findByCodigo(String codigo) {
        return cupomRepository.findByCodigo(codigo).stream().map(CupomResponseDTO::valueOf).toList();
    }

    @Override
    public List<CupomResponseDTO> findByFornecedor(Long idFornecedor) {
        return cupomRepository.findByFornecedor(idFornecedor).stream().map(CupomResponseDTO::valueOf).toList();
    }   
}