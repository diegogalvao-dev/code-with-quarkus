package guitarras.acme.dto;

import guitarras.acme.model.Guitarra;
import guitarras.acme.model.GuitarraAcustica;
import guitarras.acme.model.GuitarraEletrica;
import guitarras.acme.model.Modelos;

public record GuitarrasResponseDTO(
        Long id,
        String nome,
        String tipo,
        Double price,
        Integer estoque,
        Modelos modelos
) {

    public static GuitarrasResponseDTO valueOf(Guitarra guitarra) {

        if (guitarra == null) {
            return null;
        }

        String tipoStr = null;

        if (guitarra instanceof GuitarraEletrica) {
            tipoStr = "ELETRICA";
        } else if (guitarra instanceof GuitarraAcustica) {
            tipoStr = "ACUSTICA";
        } else {
            tipoStr = "DESCONHECIDO";
        }

        return new GuitarrasResponseDTO(
                guitarra.getId(),
                guitarra.getNome(),
                tipoStr,
                guitarra.getPrice(),
                guitarra.getEstoque(),
                guitarra.getModelos()
        );
    }
}