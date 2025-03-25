package guitarras.acme.service;


import guitarras.acme.dto.CordaDTO;
import guitarras.acme.dto.CordaResponseDTO;
import guitarras.acme.model.Corda;
import guitarras.acme.model.Guitarra;
import guitarras.acme.repository.CordaRepository;
import guitarras.acme.repository.GuitarraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CordaServiceImpl implements CordaService {

    @Inject
    CordaRepository cordaRepository;

    @Inject
    GuitarraRepository guitarraRepository;

    @Override
    @Transactional
    public CordaResponseDTO create(CordaDTO dto) {
        Corda novaCorda = new Corda();
        novaCorda.setCalibre(dto.calibre());


        Guitarra guitarra = guitarraRepository.findById(dto.idguitarra());

        novaCorda.setGuitarra(guitarra);

        cordaRepository.persist(novaCorda);

        return CordaResponseDTO.valueOf(novaCorda);
    }

    @Override
    @Transactional
    public void update(long id, CordaDTO dto) {
        Corda edicaoCorda = cordaRepository.findById(id);

        edicaoCorda.setCalibre(dto.calibre());
        Guitarra guitarra = guitarraRepository.findById(dto.idguitarra());
        edicaoCorda.setGuitarra(guitarra);
    }

    @Override
    @Transactional
    public void delete(long id) {
        cordaRepository.deleteById(id);
    }

    @Override
    public CordaResponseDTO findById(long id) {
        return CordaResponseDTO.valueOf(cordaRepository.findById(id));
    }

    @Override
    public List<CordaResponseDTO> findByCalibre(String calibre) {
        return cordaRepository.findByCalibre(calibre).stream().map(e -> CordaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<CordaResponseDTO> findAll() {
        return cordaRepository.findAll().stream().map(e -> CordaResponseDTO.valueOf(e)).toList();
    }

}
