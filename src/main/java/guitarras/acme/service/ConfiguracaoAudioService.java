package guitarras.acme.service;

import guitarras.acme.dto.ConfiguracaoAudioDTO;
import guitarras.acme.dto.ConfiguracaoAudioResponseDTO;

import java.util.List;

public interface ConfiguracaoAudioService {

    ConfiguracaoAudioResponseDTO create(ConfiguracaoAudioDTO configuracaoaudio);
    void update(long id, ConfiguracaoAudioDTO configuracaoaudio);
    void delete(long id);
    
    List<ConfiguracaoAudioResponseDTO> findAll();

}
