package guitarras.acme.dto;

import guitarras.acme.model.Guitarra;
import guitarras.acme.model.GuitarraAcustica;
import guitarras.acme.model.GuitarraEletrica;
import guitarras.acme.model.Modelos;

// Este DTO agora é genérico. Não inclui campos específicos.
// Para campos específicos, considere criar DTOs como GuitarraEletricaResponseDTO.
public record GuitarrasResponseDTO(
        Long id,
        String nome,
        String tipo, // Mantemos o tipo como String para compatibilidade/simplicidade
        Modelos modelos
        // Adicione aqui campos específicos se quiser um DTO mais completo,
        // mas eles seriam null para o outro tipo. Ex: Integer numeroCaptadores
) {

    public static GuitarrasResponseDTO valueOf(Guitarra guitarra) {

        if (guitarra == null) {
            return null;
        }

        String tipoStr = null;
        // Verifica o tipo real da instância
        if (guitarra instanceof GuitarraEletrica) {
            tipoStr = "ELETRICA";
            // Se quisesse adicionar campos específicos ao DTO:
            // GuitarraEletrica eletrica = (GuitarraEletrica) guitarra;
            // Integer caps = eletrica.getNumeroCaptadores();
            // return new GuitarrasResponseDTO(..., caps);
        } else if (guitarra instanceof GuitarraAcustica) {
            tipoStr = "ACUSTICA";
            // GuitarraAcustica acustica = (GuitarraAcustica) guitarra;
            // ... pegar campos específicos
        } else {
            // Caso exista uma instância de Guitarra base (se não for abstrata)
            // ou outro tipo não mapeado. Defina um default ou lance erro.
            tipoStr = "DESCONHECIDO";
        }

        return new GuitarrasResponseDTO(
                guitarra.getId(),
                guitarra.getNome(),
                tipoStr, // Usa a string determinada acima
                guitarra.getModelos()
        );
    }
}