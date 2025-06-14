package guitarras.acme.dto;

import guitarras.acme.model.Endereco;
import guitarras.acme.model.Pedido;
import guitarras.acme.model.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        LocalDateTime date,
        Double totalPedido,
        List<ItemPedidoResponseDTO> lista,
        EnderecoResponseDTO endereco,
        StatusPedido status


) {

    public static PedidoResponseDTO valueOf(Pedido pedido){
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getDataHora(),
                pedido.getTotalPedido(),
                pedido.getItens().stream().map(i -> ItemPedidoResponseDTO.valueOf(i)).toList(),
                EnderecoResponseDTO.valueOf(pedido.getEndereco()),
                pedido.getStatus()

        );
    }

}
