package guitarras.acme.dto;

import guitarras.acme.model.EstiloCase;
import guitarras.acme.model.Estojo;

public record EstojoResponseDTO(
        Long id,
        String material,
        EstiloCase estiloCase) {

    public static EstojoResponseDTO valueOf(Estojo estojo){

        if (estojo == null){
            return null;
        }

        return new EstojoResponseDTO(estojo.getId(), estojo.getMaterial(), estojo.getEstiloCase());

    }

}