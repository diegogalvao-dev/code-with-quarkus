package guitarras.acme.dto;


import guitarras.acme.model.Endereco;
import guitarras.acme.model.Usuario;

public record EnderecoResponseDTO(
        Long id,
        String quadra,
        Integer lote,
        UsuarioResponseDTO User) {

    public static EnderecoResponseDTO valueOf(Endereco endereco){

        if (endereco == null){
            return null;
        }

        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getQuadra(),
                endereco.getLote(),
                UsuarioResponseDTO.valueOf(endereco.getUsuario())

        );

    }

}
