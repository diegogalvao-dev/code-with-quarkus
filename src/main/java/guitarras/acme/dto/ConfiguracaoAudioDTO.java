package guitarras.acme.dto;


import jakarta.validation.constraints.NotNull;

public record ConfiguracaoAudioDTO(

        @NotNull(message = "O tipo do amplificador não pode ser nulo ou vazio.")
        String tipoAmplificador,
        @NotNull(message = "O preset do equalizador não pode ser nulo ou vazio.")
        String presetEqualizador,

        boolean temPedaleira) {

}