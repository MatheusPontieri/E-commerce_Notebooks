package br.notelab.service.pedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.function.DoubleBinaryOperator;

import br.notelab.dto.pedido.PedidoDTO;
import br.notelab.dto.pedido.PedidoResponseDTO;
import br.notelab.dto.pedido.item_pedido.ItemPedidoDTO;
import br.notelab.model.notebook.Notebook;
import br.notelab.model.pedido.Cupom;
import br.notelab.model.pedido.ItemPedido;
import br.notelab.model.pedido.Pedido;
import br.notelab.repository.ClienteRepository;
import br.notelab.repository.CupomRepository;
import br.notelab.repository.NotebookRepository;
import br.notelab.repository.PedidoRepository;
import br.notelab.validation.ValidationException;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

public class PedidoServiceImpl implements PedidoService {

    @Inject
    public PedidoRepository pedidoRepository;

    @Inject
    public NotebookRepository notebookRepository;

    @Inject
    public ClienteRepository clienteRepository;

    @Inject
    public CupomRepository cupomRepository;

    @Override
    public PedidoResponseDTO create(@Valid PedidoDTO dto) {
        Pedido p = new Pedido();

        p.setCliente(clienteRepository.findById(dto.idCliente()));
        p.setData(LocalDateTime.now());

        List<ItemPedido> listaItens = getItensFromDTO(dto.itens());
        p.setListaItem(listaItens);
        p.setTotal(calculateTotalPedido(listaItens));

        p.setListaStatus(null);

        pedidoRepository.persist(p);
        return PedidoResponseDTO.valueOf(p);
    }

    @Override
    public void update(Long id, @Valid PedidoDTO dto) {

    }

    @Override
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        return PedidoResponseDTO.valueOf(pedidoRepository.findById(id));
    }

    @Override
    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository.listAll().stream().map(PedidoResponseDTO::valueOf).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByCliente(Long idCliente) {
        return pedidoRepository.findByCliente(idCliente).stream().map(PedidoResponseDTO::valueOf).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByItem(Long idNotebook) {
        return pedidoRepository.findByItem(idNotebook).stream().map(PedidoResponseDTO::valueOf).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByStatus(Integer idStatus) {
        return pedidoRepository.findByStatus(idStatus).stream().map(PedidoResponseDTO::valueOf).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByTotalAcimaMinimo(Double total) {
        return pedidoRepository.findByTotalAcimaMinimo(total).stream().map(PedidoResponseDTO::valueOf).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByTotalAbaixoMaximo(Double total) {
        return pedidoRepository.findByTotalAbaixoMaximo(total).stream().map(PedidoResponseDTO::valueOf).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByDataMinima(LocalDateTime data) {
        return pedidoRepository.findByDataMinima(data).stream().map(PedidoResponseDTO::valueOf).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByDataMaxima(LocalDateTime data) {
        return pedidoRepository.findByDataMaxima(data).stream().map(PedidoResponseDTO::valueOf).toList();
    }   

    private List<ItemPedido> getItensFromDTO(List<ItemPedidoDTO> listaItemDTO) throws ValidationException{
        listaItemDTO.forEach(i -> verificarEstoque(i.idNotebook(), i.quantidade()));
        listaItemDTO.forEach(i -> verificarValidadeCupom(i.idCupom()));
        listaItemDTO.forEach(i -> verificarCupomFornecedor(i.idCupom(), i.idNotebook()));

        List<ItemPedido> itens = new ArrayList<>();

        for (ItemPedidoDTO itemDTO : listaItemDTO) {
            ItemPedido item = new ItemPedido();
            Notebook n = notebookRepository.findById(itemDTO.idNotebook());
            Cupom c = cupomRepository.findById(itemDTO.idCupom());

            item.setNotebook(n);
            item.setQuantidade(itemDTO.quantidade());
            item.setCupom(c);

            Double totalItem = (item.getQuantidade() * n.getPreco());
            Double desconto = totalItem * c.getPercentualDesconto();
            item.setPreco(totalItem - desconto);

            itens.add(item);
        }

        return itens;
    }

    private Double calculateTotalPedido(List<ItemPedido> listaItem){
        DoubleAccumulator total = new DoubleAccumulator((x, y) -> x + y, 0);
        listaItem.forEach(i -> total.accumulate(i.getPreco()));
        
        return total.get();
    }

    private boolean verificarEstoque(Long idNotebook, Integer quantidade){
        boolean temEstoque = notebookRepository.findById(idNotebook).getEstoque() >= quantidade;
        
        if (!temEstoque)
            throw new ValidationException("estoque", "Não há estoque suficiente");

        return true;
    }

    private boolean verificarValidadeCupom(Long idCupom){
        boolean cupomValido = LocalDateTime.now().isBefore(cupomRepository.findById(idCupom).getDataValidade());

        if(!cupomValido)
            throw new ValidationException("cupom", "Este cupom já expirou");
        
        return true;
    }

    // Verificar se o cupom usado realmente pertence é aplicável para aquela marca (fornecedor)
    private boolean verificarCupomFornecedor(Long idCupom, Long idFornecedor){
        Long idFornecedorCupom = cupomRepository.findById(idCupom).getFornecedor().getId();
        Long idFornecedorNotebook = notebookRepository.findById(idFornecedor).getFornecedor().getId();

        if(idFornecedorCupom != idFornecedorNotebook)
            throw new ValidationException("cupom", "Este cupom não é aplicável para esta marca");

        return true;
    }
}