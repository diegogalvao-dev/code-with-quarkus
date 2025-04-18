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
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CatalogoDeArtistasServiceImpl implements CatalogoDeArtistasService {

    @Inject
    CatalogoDeArtistasRepository catalogoDeArtistasRepository;

    @Inject
    GuitarraRepository guitarraRepository;

    @Override
    @Transactional
    public CatalogoDeArtistasResponseDTO create(CatalogoDeArtistasDTO dto) {
        CatalogoDeArtistas novaCatalogoDeArtistas = new CatalogoDeArtistas();
        novaCatalogoDeArtistas.setName(dto.name());

        Guitarra guitarra = guitarraRepository.findById(dto.idGuitarra());
        novaCatalogoDeArtistas.setGuitarras(List.of(guitarra));

        novaCatalogoDeArtistas.setEstiloMusical(EstiloMusical.valueOf(dto.idEstiloMusical()));

        catalogoDeArtistasRepository.persist(novaCatalogoDeArtistas);

        return CatalogoDeArtistasResponseDTO.valueOf(novaCatalogoDeArtistas);
    }

    @Override
    @Transactional
    public void update(long id, CatalogoDeArtistasDTO dto) {
        CatalogoDeArtistas edicaoCatalogoDeArtistas = catalogoDeArtistasRepository.findById(id);

        edicaoCatalogoDeArtistas.setName(dto.name());

        Guitarra guitarra = guitarraRepository.findById(dto.idGuitarra());
        edicaoCatalogoDeArtistas.setGuitarras(List.of(guitarra));

        edicaoCatalogoDeArtistas.setEstiloMusical(EstiloMusical.valueOf(dto.idEstiloMusical()));
    }

    @Override
    @Transactional
    public void delete(long id) {
        catalogoDeArtistasRepository.deleteById(id);
    }

    @Override
    public CatalogoDeArtistasResponseDTO findById(long id) {
        return CatalogoDeArtistasResponseDTO.valueOf(catalogoDeArtistasRepository.findById(id));
    }

    @Override
    public CatalogoDeArtistasResponseDTO findByName(String name) {
        return CatalogoDeArtistasResponseDTO.valueOf(catalogoDeArtistasRepository.findByName(name));
    }

    @Override
    public List<CatalogoDeArtistasResponseDTO> findAll() {
        return catalogoDeArtistasRepository.findAll().stream().map(e -> CatalogoDeArtistasResponseDTO.valueOf(e)).toList();
    }

}
