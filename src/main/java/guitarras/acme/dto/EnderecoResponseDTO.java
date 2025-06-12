package guitarras.acme.dto;

import guitarras.acme.model.Corda;
import guitarras.acme.model.Endereco;
import guitarras.acme.model.Usuario;

public record EnderecoResponseDTO(Long id, String quadra, Integer lote, Usuario User) {

    public static EnderecoResponseDTO valueOf(Endereco endereco){

        if (endereco == null){
            return null;
        }

        return new EnderecoResponseDTO(endereco.getId(), endereco.getQuadra(), endereco.getLote(), endereco.getUsuario());

    }

}
