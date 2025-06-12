package guitarras.acme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(

        @NotBlank(message = "a quadra não pode ficar vazia")
        String quadra,

        @NotNull(message = "a lote não pode ficar vazia")
        Integer lote,

        @NotNull(message = "todo endereço precisa de um user")
        Long idUser) {
}
