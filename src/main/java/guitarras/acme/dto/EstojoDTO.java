package guitarras.acme.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EstojoDTO(

        @NotNull(message = "O material do estojo não pode ser nulo ou vazio.")
        @Size(max = 20, message = "O material não pode exceder 20 caracteres.")
        String material,

        @NotNull(message = "O ID do estilo do case não pode ser nulo.")
        Integer idEstiloCase) {

}