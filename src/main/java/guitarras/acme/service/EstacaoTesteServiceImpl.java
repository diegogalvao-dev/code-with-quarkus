package guitarras.acme.service;


import guitarras.acme.dto.*;
import guitarras.acme.model.ConfiguracaoAudio;
import guitarras.acme.model.Corda;
import guitarras.acme.model.EstacaoTeste;
import guitarras.acme.model.Guitarra;
import guitarras.acme.repository.ConfiguracaoAudioRepository;
import guitarras.acme.repository.CordaRepository;
import guitarras.acme.repository.EstacaoTesteRepository;
import guitarras.acme.repository.GuitarraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
public class EstacaoTesteServiceImpl implements EstacaoTesteService {

    @Inject
    EstacaoTesteRepository estacaoTesteRepository;

    @Inject
    ConfiguracaoAudioRepository configuracaoAudioRepository;


    @Override
    @Transactional
    public EstacaoTesteResponseDTO create (EstacaoTesteDTO dto) {
        EstacaoTeste novaEstacao = new EstacaoTeste();
        novaEstacao.setName(dto.name());

        novaEstacao.setOcupada(dto.ocupada());
        novaEstacao.setLocalizacao(dto.localizacao());

        novaEstacao.setConfiguracaoAudio(configuracaoAudioRepository.findById(dto.idconfiguracaoAudio()));

        estacaoTesteRepository.persist(novaEstacao);

        return EstacaoTesteResponseDTO.valueOf(novaEstacao);
    }


    @Override
    @Transactional
    public void update(long id, EstacaoTesteDTO dto) {
        EstacaoTeste estacaoAlterada = estacaoTesteRepository.findById(id);

        if (estacaoAlterada == null) {
            throw new NotFoundException("EstacaoTeste não encontrada para atualização com ID: " + id);
        }

        estacaoAlterada.setName(dto.name());

        ConfiguracaoAudio configuracaoAudio = configuracaoAudioRepository.findById(dto.idconfiguracaoAudio());
        estacaoAlterada.setConfiguracaoAudio(configuracaoAudio);

        estacaoAlterada.setOcupada(dto.ocupada());

        estacaoAlterada.setLocalizacao(dto.localizacao());

    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        EstacaoTeste estacaoTesteDeletar = estacaoTesteRepository.findByName(name).orElseThrow(() -> new NotFoundException("EstacaoTeste não encontrada com esse nome" + name));
        estacaoTesteRepository.delete(estacaoTesteDeletar);
    }

    @Override
    @Transactional
    public List<EstacaoTesteResponseDTO> findAll() {
        return estacaoTesteRepository.listAll().stream()
                .map(EstacaoTesteResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<EstacaoTesteResponseDTO> findByNaoOcupada(){
        return estacaoTesteRepository.findByOcupada(false).stream().map(EstacaoTesteResponseDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EstacaoTesteResponseDTO findById(long id) {
        EstacaoTeste estacaoTeste = estacaoTesteRepository.findById(id);
        if (estacaoTeste == null) {
            throw new NotFoundException("estacao não encontrada: " + id);
        }
        return EstacaoTesteResponseDTO.valueOf(estacaoTeste);
    }

}
