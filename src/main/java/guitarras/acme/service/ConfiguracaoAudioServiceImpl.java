package guitarras.acme.service;


import guitarras.acme.dto.ConfiguracaoAudioDTO;
import guitarras.acme.dto.ConfiguracaoAudioResponseDTO;
import guitarras.acme.model.ConfiguracaoAudio;
import guitarras.acme.model.ConfiguracaoAudio;
import guitarras.acme.model.Guitarra;
import guitarras.acme.repository.ConfiguracaoAudioRepository;
import guitarras.acme.repository.ConfiguracaoAudioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ConfiguracaoAudioServiceImpl implements ConfiguracaoAudioService {

    @Inject
    ConfiguracaoAudioRepository configuracaoAudioRepository;
    
    @Override
    @Transactional
    public ConfiguracaoAudioResponseDTO create (ConfiguracaoAudioDTO dto) {
        ConfiguracaoAudio novaConfiguracao = new ConfiguracaoAudio();
        novaConfiguracao.setTipoAmplificador(dto.tipoAmplificador());

        novaConfiguracao.setPresetEqualizador(dto.presetEqualizador());
        
        novaConfiguracao.setTemPedaleira(dto.temPedaleira());
        
        configuracaoAudioRepository.persist(novaConfiguracao);

        return ConfiguracaoAudioResponseDTO.valueOf(novaConfiguracao);
    }


    @Override
    @Transactional
    public void update(long id, ConfiguracaoAudioDTO dto) {
        ConfiguracaoAudio configuracaoAlterada = configuracaoAudioRepository.findById(id);

        configuracaoAlterada.setTipoAmplificador(dto.tipoAmplificador());

        configuracaoAlterada.setPresetEqualizador(dto.presetEqualizador());

        configuracaoAlterada.setTemPedaleira(dto.temPedaleira());

    }

    @Override
    @Transactional
    public void delete(long id) {
        configuracaoAudioRepository.deleteById(id);
    }

    @Override
    public List<ConfiguracaoAudioResponseDTO> findAll() {
        return configuracaoAudioRepository.findAll().stream().map(e -> ConfiguracaoAudioResponseDTO.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public ConfiguracaoAudioResponseDTO findById(Long id) {
        return ConfiguracaoAudioResponseDTO.valueOf(configuracaoAudioRepository.findById(id));
    }

}
