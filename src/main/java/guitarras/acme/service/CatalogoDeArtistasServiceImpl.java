package guitarras.acme.service;


import guitarras.acme.dto.CatalogoDeArtistasDTO;
import guitarras.acme.dto.CatalogoDeArtistasResponseDTO;
import guitarras.acme.model.*;
import guitarras.acme.model.CatalogoDeArtistas;
import guitarras.acme.repository.CatalogoDeArtistasRepository;
import guitarras.acme.repository.CatalogoDeArtistasRepository;
import guitarras.acme.repository.GuitarraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CatalogoDeArtistasServiceImpl implements CatalogoDeArtistasService {

    @Inject
    EntityManager em;

    private CatalogoDeArtistasResponseDTO toResponseDTO(CatalogoDeArtistas artista) {
        List<Long> guitarrasIds = (artista.getGuitarras() != null) ?
                artista.getGuitarras().stream().map(Guitarra::getId).collect(Collectors.toList()) :
                null;
        return new CatalogoDeArtistasResponseDTO(artista.getId(), artista.getName(), guitarrasIds, artista.getEstiloMusical());
    }

    @Inject
    CatalogoDeArtistasRepository catalogoDeArtistasRepository;

    @Inject
    GuitarraRepository guitarraRepository;

    @Override
    @Transactional
    public CatalogoDeArtistas create(CatalogoDeArtistasDTO dto) {
        CatalogoDeArtistas novoArtista = new CatalogoDeArtistas();
        novoArtista.setName(dto.name());

        novoArtista.setEstiloMusical(EstiloMusical.valueOf(dto.idEstiloMusical()));

        em.persist(novoArtista);

        return novoArtista;
    }

    @Override
    @Transactional
    public CatalogoDeArtistas addGuitarra(Long artistaId, Long guitarraId) {
        CatalogoDeArtistas artista = em.find(CatalogoDeArtistas.class, artistaId);
        Guitarra guitarra = em.find(Guitarra.class, guitarraId);

        if (artista == null || guitarra == null) {
            return null;
        }

        if (artista.getGuitarras() == null) {
            artista.setGuitarras(new java.util.ArrayList<>());
        }
        if (!artista.getGuitarras().contains(guitarra)) {
            artista.getGuitarras().add(guitarra);
            em.merge(artista);
        }
        return artista;
    }

    @Override
    @Transactional
    public CatalogoDeArtistas update(long id, CatalogoDeArtistasDTO dto) {
        CatalogoDeArtistas edicaoCatalogoDeArtistas = catalogoDeArtistasRepository.findById(id);

        if (edicaoCatalogoDeArtistas == null) {
            return null;
        }

        edicaoCatalogoDeArtistas.setName(dto.name());

        em.merge(catalogoDeArtistasRepository);

        return edicaoCatalogoDeArtistas;
    }

    @Override
    @Transactional
    public void delete(long id) {
        catalogoDeArtistasRepository.deleteById(id);
    }

    @Override
    public CatalogoDeArtistasResponseDTO findById(long id) {
//        return CatalogoDeArtistasResponseDTO.valueOf(catalogoDeArtistasRepository.findById(id));
        CatalogoDeArtistas artista = em.find(CatalogoDeArtistas.class, id);
        return (artista != null) ? toResponseDTO(artista) : null;
    }

//    @Override
//    public CatalogoDeArtistasResponseDTO findByName(String name) {
//        return CatalogoDeArtistasResponseDTO.valueOf(catalogoDeArtistasRepository.findByName(name));
//    }

    @Override
    public List<CatalogoDeArtistasResponseDTO> findAll() {
        return em.createQuery("SELECT a FROM CatalogoDeArtistas a", CatalogoDeArtistas.class)
                .getResultList()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
//        return catalogoDeArtistasRepository.findAll().stream()
//                .map(e -> CatalogoDeArtistasResponseDTO.valueOf(e))
//                .collect(Collectors.toList());
    }

}
