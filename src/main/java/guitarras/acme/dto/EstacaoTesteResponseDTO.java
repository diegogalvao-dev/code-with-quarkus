package guitarras.acme.dto;

import guitarras.acme.model.ConfiguracaoAudio;
import guitarras.acme.model.EstacaoTeste;

public record EstacaoTesteResponseDTO(Long id, String name, boolean ocupada, String localizacao, ConfiguracaoAudio configuracaoAudio) {

    public static EstacaoTesteResponseDTO valueOf(EstacaoTeste estacaoTeste){

        if (estacaoTeste == null){
            return null;
        }

        return new EstacaoTesteResponseDTO(estacaoTeste.getId(), estacaoTeste.getName(), estacaoTeste.isOcupada(), estacaoTeste.getLocalizacao(), estacaoTeste.getConfiguracaoAudio());

    }

}