package br.notelab.service.pedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;

import br.notelab.dto.pagamento.BoletoResponseDTO;
import br.notelab.dto.pagamento.CartaoDTO;
import br.notelab.dto.pagamento.PixResponseDTO;
import br.notelab.dto.pedido.PedidoDTO;
import br.notelab.dto.pedido.PedidoResponseDTO;
import br.notelab.dto.pedido.item_pedido.ItemPedidoDTO;
import br.notelab.model.notebook.Notebook;
import br.notelab.model.pagamento.Boleto;
import br.notelab.model.pagamento.Cartao;
import br.notelab.model.pagamento.Pix;
import br.notelab.model.pedido.Cupom;
import br.notelab.model.pedido.ItemPedido;
import br.notelab.model.pedido.Pedido;
import br.notelab.model.pedido.Status;
import br.notelab.model.pedido.StatusPedido;
import br.notelab.model.pessoa.Fornecedor;
import br.notelab.repository.ClienteRepository;
import br.notelab.repository.CupomRepository;
import br.notelab.repository.NotebookRepository;
import br.notelab.repository.PagamentoRepository;
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
    public PagamentoRepository pagamentoRepository;

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

        // Arrumar
        List<StatusPedido> listaStatus = Arrays.asList(createStatusPedido(1));
        p.setListaStatus(listaStatus);

        p.setPagamento(null);

        pedidoRepository.persist(p);
        return PedidoResponseDTO.valueOf(p);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid PedidoDTO dto) {
        Pedido p = pedidoRepository.findById(id);

        List<ItemPedido> itensFromDTO = getItensFromDTO(dto.itens());
        List<ItemPedido> itensFromBanco = p.getListaItem();

        itensFromBanco.clear();
        itensFromDTO.forEach(i -> itensFromBanco.add(i));

        p.setTotal(calculateTotalPedido(itensFromBanco));
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
    @Transactional
    public PixResponseDTO gerarInformacoesPix(Long idPedido){
        Double total = pedidoRepository.findById(idPedido).getTotal();
        
        Pix pix = new Pix();
        pix.setValor(total);
        pix.setDataLimite(LocalDateTime.now().plusHours(1));
        pix.setChaveDestinatario("notelab_store@gmail.com");
        pix.setIdentificador("valor aleatório ;-;");

        pagamentoRepository.persist(pix);
        return PixResponseDTO.valueOf(pix);
    }

    @Override
    @Transactional
    public BoletoResponseDTO gerarInformacoesBoleto(Long idPedido){
        Double total = pedidoRepository.findById(idPedido).getTotal();

        Boleto boleto = new Boleto();
        boleto.setValor(total);
        boleto.setDataLimite(LocalDateTime.now().plusHours(10));
        boleto.setCodigo("valor aleatório ;-;");

        pagamentoRepository.persist(boleto);
        return BoletoResponseDTO.valueOf(boleto);
    }

    @Override
    public void registrarPagamentoPix(Long idPedido, Long idPix){
        Pedido p = pedidoRepository.findById(idPedido);
        p.setPagamento(pagamentoRepository.findById(idPix));
    }

    @Override
    public void registrarPagamentoBoleto(Long idPedido, Long idBoleto){
        Pedido p = pedidoRepository.findById(idPedido);
        p.setPagamento(pagamentoRepository.findById(idBoleto));
    }

    @Override
    public void registrarPagamentoCartao(Long idPedido, CartaoDTO cartao){
        Pedido p = pedidoRepository.findById(idPedido);
        
        Cartao c = new Cartao();
        c
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