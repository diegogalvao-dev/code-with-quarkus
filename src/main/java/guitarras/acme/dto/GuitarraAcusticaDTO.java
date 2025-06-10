package guitarras.acme.dto;

public record GuitarraAcusticaDTO(
        String nome,
        Integer idModelo,
        Double price,
        String tipoMadeiraTampo,
        Boolean possuiCutaway,
        Boolean eletroacustica,
        Integer estoque
) {
}