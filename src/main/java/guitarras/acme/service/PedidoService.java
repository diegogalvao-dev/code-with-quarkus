package guitarras.acme.service;

import guitarras.acme.dto.PedidoDTO;
import guitarras.acme.dto.PedidoResponseDTO;
import guitarras.acme.repository.PedidoRepository;

import java.util.List;

public interface PedidoService {

    List<PedidoResponseDTO> findByUsername(String username);
    PedidoResponseDTO findById(long idPedido);
    List<PedidoResponseDTO> historicoDeCompras();

    PedidoResponseDTO create(PedidoDTO pedido, String username);
    void delete(long id);
    PedidoResponseDTO ProcessamentoPagamento(Long idpedido, boolean aprovado);

}
