package guitarras.acme.dto;

import guitarras.acme.model.Guitarra;
import guitarras.acme.model.Modelos;

public record GuitarrasResponseDTO(Long id, String nome, String tipo, Modelos modelos) {

    public static GuitarrasResponseDTO valueOf(Guitarra guitarra){

        if (guitarra == null){
            return null;
        }

        return new GuitarrasResponseDTO(guitarra.getId(), guitarra.getNome(), guitarra.getTipo(), guitarra.getModelos());

    }

}