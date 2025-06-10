package guitarras.acme.dto;

public record GuitarraEletricaDTO(
        String nome,
        Integer idModelo,
        Double price,
        Integer estoque,
        Integer numeroCaptadores,
        String tipoPonte
) {
}