package guitarras.acme.dto;

import guitarras.acme.model.ConfiguracaoAudio;
import guitarras.acme.model.EstacaoTeste;

public record EstacaoTesteResponseDTO(Long id, String name, boolean ocupada, String localizacao, ConfiguracaoAudioResponseDTO configuracaoAudio) {

    public static EstacaoTesteResponseDTO valueOf(EstacaoTeste estacaoTeste){

        ConfiguracaoAudioResponseDTO configDTO = null; // Começa como null
        if (estacaoTeste.getConfiguracaoAudio() != null) {
            configDTO = ConfiguracaoAudioResponseDTO.valueOf(estacaoTeste.getConfiguracaoAudio());
        }

        return new EstacaoTesteResponseDTO(estacaoTeste.getId(), estacaoTeste.getName(), estacaoTeste.isOcupada(), estacaoTeste.getLocalizacao(), configDTO);

    }

}