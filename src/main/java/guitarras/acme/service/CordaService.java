package guitarras.acme.service;

import guitarras.acme.dto.CordaDTO;
import guitarras.acme.dto.CordaResponseDTO;

import java.util.List;

public interface CordaService {

    CordaResponseDTO create(CordaDTO Corda);
    void update(long id, CordaDTO Corda);
    long deleteByIdGuitarra(long idGuitarra);
    void delete(long id);

    CordaResponseDTO findById(long id);
    List<CordaResponseDTO> findByPorGuitarra(Long idGuitarra);
    List<CordaResponseDTO> findAll();

}
