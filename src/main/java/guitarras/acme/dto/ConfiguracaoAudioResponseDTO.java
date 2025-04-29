package guitarras.acme.dto;

import guitarras.acme.model.ConfiguracaoAudio;
import guitarras.acme.model.EstiloMusical;

import java.util.List;

public record ConfiguracaoAudioResponseDTO(Long id, String tipoAmplificador, String presetEqualizador, boolean temPedaleira) {

    public static ConfiguracaoAudioResponseDTO valueOf(ConfiguracaoAudio configuracaoAudio){

        if (configuracaoAudio == null){
            return null;
        }

        return new ConfiguracaoAudioResponseDTO(configuracaoAudio.getId(), configuracaoAudio.getTipoAmplificador(), configuracaoAudio.getPresetEqualizador(), configuracaoAudio.isTemPedaleira());

    }
    
}