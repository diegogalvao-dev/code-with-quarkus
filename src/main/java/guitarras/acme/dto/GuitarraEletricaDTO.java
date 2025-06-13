package guitarras.acme.dto;

import jakarta.validation.constraints.*;

public record GuitarraEletricaDTO(

        @NotBlank(message = "O nome da guitarra elétrica não pode ser nulo ou em branco.")
        @Size(max = 100, message = "O nome da guitarra elétrica não pode exceder 100 caracteres.")
        String nome,

        @NotNull(message = "O ID do modelo da guitarra elétrica não pode ser nulo.")
        Integer idModelo,

        @NotNull(message = "O preço da guitarra elétrica não pode ser nulo.")
        @PositiveOrZero(message = "O preço da guitarra elétrica deve ser positivo ou zero.")
        Double price,

        @NotNull(message = "O estoque da guitarra elétrica não pode ser nulo.")
        @Min(value = 0, message = "O estoque da guitarra elétrica não pode ser negativo.")
        Integer estoque,

        @NotNull(message = "O número de captadores não pode ser nulo.")
        @Min(value = 1, message = "A guitarra elétrica deve ter pelo menos 1 captador.")
        Integer numeroCaptadores,

        @NotBlank(message = "O tipo da ponte da guitarra elétrica não pode ser nulo ou em branco.")
        @Size(max = 50, message = "O tipo da ponte não pode exceder 50 caracteres.")
        String tipoPonte
) {
}