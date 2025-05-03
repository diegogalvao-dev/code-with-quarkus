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
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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

        if (guitarra == null) {
            throw new NotFoundException("Guitarra com ID " + dto.idguitarra() + " não encontrada para associação.");
        }

        novaCorda.setGuitarra(guitarra);

        cordaRepository.persist(novaCorda);

        return CordaResponseDTO.valueOf(novaCorda);
    }

    @Override
    @Transactional
    public void update(long id, CordaDTO dto) {
        Corda edicaoCorda = cordaRepository.findById(id);

        if (edicaoCorda == null) {
            throw new NotFoundException("Corda com ID " + id + " não encontrada para atualização.");
        }

        edicaoCorda.setCalibre(dto.calibre());
        Guitarra guitarra = guitarraRepository.findById(dto.idguitarra());
        if (guitarra == null) {
            throw new NotFoundException("Guitarra com ID " + dto.idguitarra() + " não encontrada para associação na atualização.");
        }
        edicaoCorda.setGuitarra(guitarra);
    }

    @Override
    @Transactional
    public void delete(long id) {
        cordaRepository.deleteById(id);
    }


    @Override
    @Transactional
    public long deleteByIdGuitarra(long idGuitarra) {
        return cordaRepository.deleteByIdGuitarra(idGuitarra);
    }

    @Override
    @Transactional
    public CordaResponseDTO findById(long id) {
        Corda corda = cordaRepository.findById(id);
        if (corda == null) {
            throw new NotFoundException("Corda com ID " + id + " não encontrada.");
        }
        return CordaResponseDTO.valueOf(cordaRepository.findById(id));
    }

    @Override
    @Transactional
    public List<CordaResponseDTO> findAll() {
        return cordaRepository.findAll().stream().map(e -> CordaResponseDTO.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public List<CordaResponseDTO> findByPorGuitarra(Long idGuitarra){
        return cordaRepository.findByIdGuitarra(idGuitarra).stream().map(CordaResponseDTO::valueOf).collect(Collectors.toList());
    }

}
