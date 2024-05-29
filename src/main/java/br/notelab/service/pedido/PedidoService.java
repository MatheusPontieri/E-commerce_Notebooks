package br.notelab.service.pedido;

import java.time.LocalDateTime;
import java.util.List;

import br.notelab.dto.pedido.PedidoDTO;
import br.notelab.dto.pedido.PedidoResponseDTO;
import jakarta.validation.Valid;

public interface PedidoService {
    PedidoResponseDTO create(@Valid PedidoDTO dto);
    void update(Long id, @Valid PedidoDTO dto);
    void delete(Long id);
    PedidoResponseDTO findById(Long id);
    List<PedidoResponseDTO> findAll();
    List<PedidoResponseDTO> findByCliente(Long idCliente);
    List<PedidoResponseDTO> findByItem(Long idNotebook);
    List<PedidoResponseDTO> findByStatus(Integer idStatus);
    List<PedidoResponseDTO> findByTotalAcimaMinimo(Double total);
    List<PedidoResponseDTO> findByTotalAbaixoMaximo(Double total);
    List<PedidoResponseDTO> findByDataMinima(LocalDateTime data);
    List<PedidoResponseDTO> findByDataMaxima(LocalDateTime data);
}