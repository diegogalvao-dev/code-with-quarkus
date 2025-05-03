package guitarras.acme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CordaDTO(

        @NotBlank(message = "O calibre da corda não pode ser nulo ou vazio.")
        @Size(max = 20, message = "O calibre não pode exceder 20 caracteres.")
        String calibre,

        @NotNull(message = "O ID da guitarra não pode ser nulo.")
        @Positive(message = "O ID da guitarra deve ser um numero positivo")
        Long idguitarra) {

}