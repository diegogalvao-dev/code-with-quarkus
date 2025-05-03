package guitarras.acme.dto;

public record GuitarraAcusticaDTO(
        String nome,
        Integer idModelo, 
        String tipoMadeiraTampo,
        Boolean possuiCutaway,
        Boolean eletroacustica
) {
}