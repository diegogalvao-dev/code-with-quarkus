package guitarras.acme.service;

import guitarras.acme.dto.EstacaoTesteDTO;
import guitarras.acme.dto.EstacaoTesteResponseDTO;
import guitarras.acme.model.EstacaoTeste;

import java.util.List;

public interface EstacaoTesteService {

    EstacaoTesteResponseDTO create(EstacaoTesteDTO estacaoTeste);
    void update(long id, EstacaoTesteDTO estacaoTeste);
    void deleteByName(String name);
    
    List<EstacaoTesteResponseDTO> findAll();
    List<EstacaoTesteResponseDTO> findByNaoOcupada();
    EstacaoTesteResponseDTO findById(long id);

}
