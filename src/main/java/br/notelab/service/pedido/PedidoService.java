package br.notelab.service.pedido;

import java.util.List;

import br.notelab.dto.pagamento.BoletoResponseDTO;
import br.notelab.dto.pagamento.CartaoDTO;
import br.notelab.dto.pagamento.PixResponseDTO;
import br.notelab.dto.pedido.PedidoDTO;
import br.notelab.dto.pedido.PedidoResponseDTO;
import jakarta.validation.Valid;

public interface PedidoService {
    PedidoResponseDTO create(@Valid PedidoDTO dto);
    void update(Long id, @Valid PedidoDTO dto);
    void delete(Long id);
    void updateStatusPedido(Long idPedido, Integer idStatus);
    PixResponseDTO gerarInformacoesPix(Long idPedido);
    BoletoResponseDTO gerarInformacoesBoleto(Long idPedido);
    void registrarPagamentoPix(Long idPedido, Long idPix);
    void registrarPagamentoBoleto(Long idPedido, Long idBoleto);
    void registrarPagamentoCartao(Long id, CartaoDTO cartao);
    PedidoResponseDTO findById(Long id);
    List<PedidoResponseDTO> findAll();
    List<PedidoResponseDTO> findByCliente(Long idCliente);
    List<PedidoResponseDTO> findByItem(Long idNotebook);
    List<PedidoResponseDTO> findByStatus(Integer idStatus);
}