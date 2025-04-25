package guitarras.acme.dto;

// DTO para CRIAR uma Guitarra Elétrica
public record GuitarraEletricaDTO(
        String nome,
        Integer idModelo, // ID do Enum Modelos
        Integer numeroCaptadores,
        String tipoPonte
) {
}