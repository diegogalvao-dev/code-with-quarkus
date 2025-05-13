package guitarras.acme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PessoaFisicaDTO(

        @NotBlank(message = "o nome não pode ficar em branco")
        @Size(max = 60, message = "O nome deve possuir no máximo 60 caracteres.")
        String nome,

        @Size(max = 11, min = 11, message = "O cpf deve possuir 11 caracteres.")
        @NotNull(message = "O cpf não pode ser nulo.")
        String cpf) {

}
