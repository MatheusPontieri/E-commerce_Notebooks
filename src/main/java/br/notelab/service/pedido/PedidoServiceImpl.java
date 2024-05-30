package br.notelab.service.pedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;

import br.notelab.dto.pedido.PedidoDTO;
import br.notelab.dto.pedido.PedidoResponseDTO;
import br.notelab.dto.pedido.item_pedido.ItemPedidoDTO;
import br.notelab.model.notebook.Notebook;
import br.notelab.model.pedido.Cupom;
import br.notelab.model.pedido.ItemPedido;
import br.notelab.model.pedido.Pedido;
import br.notelab.model.pedido.Status;
import br.notelab.model.pedido.StatusPedido;
import br.notelab.model.pessoa.Fornecedor;
import br.notelab.repository.ClienteRepository;
import br.notelab.repository.CupomRepository;
import br.notelab.repository.NotebookRepository;
import br.notelab.repository.PedidoRepository;
import br.notelab.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
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
    @Transactional
    public PedidoResponseDTO create(@Valid PedidoDTO dto) {
        Pedido p = new Pedido();

        p.setCliente(clienteRepository.findById(dto.idCliente()));
        p.setData(LocalDateTime.now());

        List<ItemPedido> listaItens = getItensFromDTO(dto.itens());
        p.setListaItem(listaItens);
        p.setTotal(calculateTotalPedido(listaItens));

        List<StatusPedido> listaStatus = Arrays.asList(createStatusPedido(3));
        p.setListaStatus(listaStatus);

        pedidoRepository.persist(p);
        return PedidoResponseDTO.valueOf(p);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid PedidoDTO dto) {
        Pedido p = pedidoRepository.findById(id);

        List<ItemPedido> listaItens = getItensFromDTO(dto.itens());
        
        p.getListaItem().clear();
        p.setListaItem(listaItens);
        p.setTotal(calculateTotalPedido(listaItens));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateStatusPedido(Long idPedido, Integer idStatus) {
        Pedido p = pedidoRepository.findById(idPedido);

        p.getListaStatus().add(createStatusPedido(idStatus));
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

    private List<ItemPedido> getItensFromDTO(List<ItemPedidoDTO> listaItemDTO){
        listaItemDTO.forEach(i -> {
            verificarEstoque(i.idNotebook(), i.quantidade());
            if (i.idCupom() != null){
                verificarValidadeCupom(i.idCupom());
                verificarCupomFornecedor(i.idCupom(), i.idNotebook());
            }
        });

        List<ItemPedido> itens = new ArrayList<>();

        for (ItemPedidoDTO itemDTO : listaItemDTO) {
            ItemPedido item = new ItemPedido();
            Notebook n = notebookRepository.findById(itemDTO.idNotebook());
            Cupom c = cupomRepository.findById(itemDTO.idCupom());

            item.setNotebook(n);
            item.setQuantidade(itemDTO.quantidade());
            item.setCupom(c);

            Double totalItem = (item.getQuantidade() * n.getPreco());
            Double desconto = 0d;
            if (c != null) desconto = totalItem * c.getPercentualDesconto();
            item.setPreco(totalItem - desconto);

            itens.add(item);
        }

        return itens;
    }

    private StatusPedido createStatusPedido(Integer id){
        StatusPedido statusPedido = new StatusPedido();

        statusPedido.setStatus(Status.valueOf(id));
        return statusPedido;
    }


    private Double calculateTotalPedido(List<ItemPedido> listaItem){
        DoubleAccumulator total = new DoubleAccumulator((x, y) -> x + y, 0);
        listaItem.forEach(i -> total.accumulate(i.getPreco()));
        
        return total.get();
    }

    private boolean verificarEstoque(Long idNotebook, Integer quantidade){
        Notebook n = notebookRepository.findById(idNotebook);
        boolean temEstoque = n.getEstoque() >= quantidade;
        
        if (!temEstoque)
            throw new ValidationException("estoque", "Não há estoque suficiente de " + n.getDescricao());

        return true;
    }

    private boolean verificarValidadeCupom(Long idCupom){
        Cupom c = cupomRepository.findById(idCupom);
        boolean cupomValido = LocalDateTime.now().isBefore(c.getDataValidade());

        if(!cupomValido)
            throw new ValidationException("cupom", "O cupom " + c.getCodigo() + " já expirou");
        
        return true;
    }

    // Verificar se o cupom usado realmente pertence é aplicável para aquela marca (fornecedor)
    private boolean verificarCupomFornecedor(Long idCupom, Long idNotebook){
        Cupom c = cupomRepository.findById(idCupom);
        Fornecedor fornecedorCupom = c.getFornecedor();
        Fornecedor fornecedorNotebook = notebookRepository.findById(idNotebook).getFornecedor();

        if(fornecedorCupom.getId() != fornecedorNotebook.getId())
            throw new ValidationException("cupom", "O cupom " + c.getCodigo() + " não é aplicável para a marca " + fornecedorNotebook.getNome());

        return true;
    }
}