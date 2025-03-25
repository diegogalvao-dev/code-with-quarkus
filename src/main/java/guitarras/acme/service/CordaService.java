package guitarras.acme.service;

import guitarras.acme.dto.CordaDTO;
import guitarras.acme.dto.CordaResponseDTO;

import java.util.List;

public interface CordaService {

    CordaResponseDTO create(CordaDTO Corda);
    void update(long id, CordaDTO Corda);
    void delete(long id);

    CordaResponseDTO findById(long id);

    List<CordaResponseDTO> findByCalibre(String calibre);
    List<CordaResponseDTO> findAll();

}
