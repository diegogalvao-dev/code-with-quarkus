package guitarras.acme.dto;


import jakarta.validation.constraints.NotNull;

public record EstacaoTesteDTO(

        @NotNull(message = "O nome da estação não pode ser nulo ou vazio.")
        String name,

        @NotNull(message = "O status da estação não pode ser nulo.")
        boolean ocupada,

        String localizacao,

        @NotNull(message = "O ID da guitarra não pode ser nulo.")
        Long idconfiguracaoAudio) {


}