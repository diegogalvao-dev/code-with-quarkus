package guitarras.acme.service;

import guitarras.acme.dto.*; // Importe os novos DTOs
import guitarras.acme.model.*; // Importe os novos Modelos
import guitarras.acme.repository.GuitarraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GuitarraServiceImpl implements GuitarraService { // Supondo que exista a interface GuitarraService

    @Inject
    GuitarraRepository guitarraRepository;

    @Override
    @Transactional
    public GuitarrasResponseDTO createEletrica(GuitarraEletricaDTO dto) {
        GuitarraEletrica novaGuitarra = new GuitarraEletrica();
        novaGuitarra.setNome(dto.nome());
        // Valide se o idModelo existe antes de usar valueOf
        try {
            novaGuitarra.setModelos(Modelos.valueOf(dto.idModelo()));
        } catch (IllegalArgumentException e) {
            // Lide com o erro - modelo inválido
            throw new IllegalArgumentException("Modelo de guitarra inválido: " + dto.idModelo());
        }
        novaGuitarra.setNumeroCaptadores(dto.numeroCaptadores());
        novaGuitarra.setTipoPonte(dto.tipoPonte());

        guitarraRepository.persist(novaGuitarra);
        return GuitarrasResponseDTO.valueOf(novaGuitarra);
    }

    @Override
    @Transactional
    public GuitarrasResponseDTO createAcustica(GuitarraAcusticaDTO dto) {
        GuitarraAcustica novaGuitarra = new GuitarraAcustica();
        novaGuitarra.setNome(dto.nome());
        try {
            novaGuitarra.setModelos(Modelos.valueOf(dto.idModelo()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Modelo de guitarra inválido: " + dto.idModelo());
        }
        novaGuitarra.setTipoMadeiraTampo(dto.tipoMadeiraTampo());
        novaGuitarra.setPossuiCutaway(dto.possuiCutaway());
        novaGuitarra.setEletroacustica(dto.eletroacustica());

        guitarraRepository.persist(novaGuitarra);
        return GuitarrasResponseDTO.valueOf(novaGuitarra);
    }


    @Override
    @Transactional
    public GuitarrasResponseDTO update(Long id, GuitarrasDTO dto) { // Usando o DTO antigo para campos comuns
        Guitarra guitarra = guitarraRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Guitarra não encontrada com id: " + id));

        guitarra.setNome(dto.nome());
        try {
            guitarra.setModelos(Modelos.valueOf(dto.idModelo()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Modelo de guitarra inválido: " + dto.idModelo());
        }

        return GuitarrasResponseDTO.valueOf(guitarra);
    }


    @Override
    @Transactional
    public void delete(long id) {
        if (!guitarraRepository.deleteById(id)) {
            throw new NotFoundException("Guitarra não encontrada com id: " + id);
        }
    }

    @Override
    public List<GuitarrasResponseDTO> findAll() {
        return guitarraRepository.listAll().stream()
                .map(GuitarrasResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuitarrasResponseDTO> findByNome(String nome) {
        // Adapte ou crie o método no repositório se necessário
        return guitarraRepository.findByNome(nome).stream()
                .map(GuitarrasResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public GuitarrasResponseDTO findById(Long id) {
        Guitarra guitarra = guitarraRepository.findById(id);
        if (guitarra == null) {
            throw new NotFoundException("Guitarra não encontrada com id: " + id);
        }
        return GuitarrasResponseDTO.valueOf(guitarra);

    }

}
