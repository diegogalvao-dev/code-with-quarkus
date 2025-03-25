package guitarras.acme.dto;

import guitarras.acme.model.Corda;
import guitarras.acme.model.Guitarra;
import guitarras.acme.model.Modelos;

public record CordaResponseDTO(Long id, String calibre, GuitarrasResponseDTO guitarra) {

    public static CordaResponseDTO valueOf(Corda corda){

        if (corda == null){
            return null;
        }

        return new CordaResponseDTO(corda.getId(), corda.getCalibre(), GuitarrasResponseDTO.valueOf(corda.getGuitarra()));

    }

}