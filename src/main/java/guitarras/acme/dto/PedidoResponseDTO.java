package guitarras.acme.dto;

import guitarras.acme.model.Endereco;
import guitarras.acme.model.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        LocalDateTime date,
        Double totalPedido,
        List<ItemPedidoResponseDTO> lista,
        Endereco endereco
        // pagamento
        //status pedido
) {

    public static PedidoResponseDTO valueOf(Pedido pedido){
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getDataHora(),
                pedido.getTotalPedido(),
                pedido.getItens().stream().map(i -> ItemPedidoResponseDTO.valueOf(i)).toList(),
                pedido.getEndereco()
        );
    }

}
