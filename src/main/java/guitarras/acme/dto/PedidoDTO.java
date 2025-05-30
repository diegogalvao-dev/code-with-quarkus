package guitarras.acme.dto;

import java.util.List;

public record PedidoDTO(
        //pagamento
        //endereco
        Double total,
        List<ItemPedidoDTO> itens
) {
}
