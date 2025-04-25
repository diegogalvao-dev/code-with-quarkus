package guitarras.acme.dto;

// DTO para CRIAR uma Guitarra Acústica
public record GuitarraAcusticaDTO(
        String nome,
        Integer idModelo, // ID do Enum Modelos
        String tipoMadeiraTampo,
        Boolean possuiCutaway,
        Boolean eletroacustica
) {
}