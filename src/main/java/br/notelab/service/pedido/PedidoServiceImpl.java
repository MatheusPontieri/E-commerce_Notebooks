package br.notelab.service.pedido;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
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
import io.quarkus.scheduler.Scheduled;
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
        p.setPrazoPagamento(LocalDateTime.now().plusSeconds(20));

        List<ItemPedido> listaItens = getItensFromDTO(dto.itens());
        p.setListaItem(listaItens);
        p.setTotal(calculateTotalPedido(listaItens));

        List<StatusPedido> listaStatus = Arrays.asList(createStatusPedido(1));
        p.setListaStatus(listaStatus);

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
        pix.setChaveDestinatario("notelab_store@gmail.com");
        pix.setIdentificador(UUID.randomUUID().toString());

        pagamentoRepository.persist(pix);
        return PixResponseDTO.valueOf(pix);
    }

    @Override
    @Transactional
    public BoletoResponseDTO gerarInformacoesBoleto(Long idPedido){
        Double total = pedidoRepository.findById(idPedido).getTotal();

        Boleto boleto = new Boleto();
        boleto.setValor(total);
        boleto.setCodigo(UUID.randomUUID().toString());

        pagamentoRepository.persist(boleto);
        return BoletoResponseDTO.valueOf(boleto);
    }

    @Override
    @Transactional
    public void registrarPagamentoPix(Long idPedido, Long idPix){
        Pedido p = pedidoRepository.findById(idPedido);
        p.setPagamento(pagamentoRepository.findById(idPix));
    }

    @Override
    @Transactional
    public void registrarPagamentoBoleto(Long idPedido, Long idBoleto){
        Pedido p = pedidoRepository.findById(idPedido);
        p.setPagamento(pagamentoRepository.findById(idBoleto));
    }

    @Override
    @Transactional
    public void registrarPagamentoCartao(Long idPedido, CartaoDTO cartaoDTO){
        Pedido p = pedidoRepository.findById(idPedido);
        
        Cartao c = CartaoDTO.convertToCartao(cartaoDTO);
        c.setValor(p.getTotal());
        
        pagamentoRepository.persist(c);
        p.setPagamento(c);
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

    private List<ItemPedido> getItensFromDTO(List<ItemPedidoDTO> listaItemDTO){
        validarListaItemDTO(listaItemDTO);

        List<ItemPedido> itens = new ArrayList<>();

        for (ItemPedidoDTO itemDTO : listaItemDTO) {
            ItemPedido item = new ItemPedido();
            Notebook n = notebookRepository.findById(itemDTO.idNotebook());
            Cupom c = cupomRepository.findById(itemDTO.idCupom());

            item.setNotebook(n);
            item.setQuantidade(itemDTO.quantidade());
            item.setCupom(c);
            item.setPreco(itemDTO.preco());

            itens.add(item);
        }

        return itens;
    }

    private StatusPedido createStatusPedido(Integer id){
        StatusPedido statusPedido = new StatusPedido();

        statusPedido.setStatus(Status.valueOf(id));
        statusPedido.setDataCadastro(LocalDateTime.now());

        return statusPedido;
    }


    private Double calculateTotalPedido(List<ItemPedido> listaItem){
        DoubleAccumulator total = new DoubleAccumulator((x, y) -> x + y, 0);
        listaItem.forEach(i -> total.accumulate(i.getPreco()));
        
        return total.get();
    }

    private void validarListaItemDTO(List<ItemPedidoDTO> listaDTO){
        for (ItemPedidoDTO item : listaDTO) {
            verificarEstoque(item.idNotebook(), item.quantidade());

            if (item.idCupom() != null){
                verificarValidadeCupom(item.idCupom());
                verificarCupomFornecedor(item.idCupom(), item.idNotebook());
            }
        }
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

    private boolean verificarCupomFornecedor(Long idCupom, Long idNotebook){
        Cupom c = cupomRepository.findById(idCupom);
        Fornecedor fornecedorCupom = c.getFornecedor();
        Fornecedor fornecedorNotebook = notebookRepository.findById(idNotebook).getFornecedor();

        if(fornecedorCupom.getId() != fornecedorNotebook.getId())
            throw new ValidationException("cupom", "O cupom " + c.getCodigo() + " não é aplicável para a marca " + fornecedorNotebook.getNome());

        return true;
    }

    @Scheduled(every = "5s") // Mudar Tempo e dataLimite para pedido
    @Transactional
    public void atualizarPedidoExpirados(){
        LocalDateTime now = LocalDateTime.now();
        List<Pedido> pedidosExpirados = pedidoRepository.findPedidosExpirados(now);

        for (Pedido p : pedidosExpirados){
            for (StatusPedido statusPedido : p.getListaStatus()) { // Verifica se já não está expirado
                if(statusPedido.getStatus().getId() == 2)
                    return;
            }

            updateStatusPedido(p.getId(), 2); // 2 -> Pagamento Expirado

            for (ItemPedido item : p.getListaItem()) { // Devolvendo ao estoque
                Notebook n = item.getNotebook();
                Integer estoque = n.getEstoque();

                n.setEstoque(estoque + item.getQuantidade());
            }
        }
    }
}