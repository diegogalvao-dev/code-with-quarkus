package guitarras.acme.service;

import guitarras.acme.dto.PedidoDTO;
import guitarras.acme.dto.PedidoResponseDTO;

import java.util.List;

public interface PedidoService {

    List<PedidoResponseDTO> findByUsername(String username);
    PedidoResponseDTO findById(long idPedido, String username);
    PedidoResponseDTO create(PedidoDTO pedido, String username);

}
