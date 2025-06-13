package guitarras.acme.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPedidoDTO (

        @NotNull(message = "O ID do produto não pode ser nulo.")
        Long idProduto,

        @NotNull(message = "O preço não pode ser nulo.")
        @Positive(message = "O preço deve ser um valor positivo.")
        Double preco,

        @NotNull(message = "A quantidade não pode ser nula.")
        @Min(value = 1, message = "A quantidade deve ser no mínimo 1.")
        Integer qtd

){
}
