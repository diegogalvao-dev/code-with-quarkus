package guitarras.acme.service;

import guitarras.acme.dto.EstacaoTesteDTO;
import guitarras.acme.dto.EstacaoTesteResponseDTO;
import guitarras.acme.model.EstacaoTeste;

import java.util.List;

public interface EstacaoTesteService {

    EstacaoTesteResponseDTO create(EstacaoTesteDTO estacaoTeste);
    void update(long id, EstacaoTesteDTO estacaoTeste);
    void delete(long id);
    
    List<EstacaoTesteResponseDTO> findAll();

}
