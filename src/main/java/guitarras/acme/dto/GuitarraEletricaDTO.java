package guitarras.acme.dto;

public record GuitarraEletricaDTO(
        String nome,
        Integer idModelo,
        Integer numeroCaptadores,
        String tipoPonte
) {
}