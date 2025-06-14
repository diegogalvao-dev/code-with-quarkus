package guitarras.acme.dto;

import guitarras.acme.model.PessoaFisica;

public record PessoaFisicaResponseDTO(Long id, String name, String cpf) {

    public static PessoaFisicaResponseDTO valueOf(PessoaFisica pessoaFisica){

        if(pessoaFisica == null){
            return null;
        }

        return new PessoaFisicaResponseDTO(
                pessoaFisica.getId(),
                pessoaFisica.getName(),
                pessoaFisica.getCpf());

    }

}
