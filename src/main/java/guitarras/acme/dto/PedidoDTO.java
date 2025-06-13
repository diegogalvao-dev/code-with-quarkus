package guitarras.acme.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record PedidoDTO(

        // @NotNull(message = "A forma de pagamento deve ser informada.")
        // Long idFormaPagamento,

        @NotNull(message = "O endereço de entrega deve ser informado.")
        Long idEndereco,

        @NotNull(message = "O total do pedido não pode ser nulo.")
        @PositiveOrZero(message = "O total do pedido deve ser um valor positivo ou zero.")
        Double total,

        @NotEmpty(message = "O pedido deve conter pelo menos um item.")
        List<@Valid ItemPedidoDTO> itens
) {
}
