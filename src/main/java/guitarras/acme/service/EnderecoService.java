package guitarras.acme.service;

import guitarras.acme.dto.EnderecoDTO;
import guitarras.acme.dto.EnderecoResponseDTO;
import guitarras.acme.repository.EnderecoRepository;

import java.util.List;

public interface EnderecoService {

    EnderecoResponseDTO create(EnderecoDTO Endereco);
    void update(long id, EnderecoDTO Endereco);
    void delete(long id);

    List<EnderecoResponseDTO> BuscarPorUser(Long idUser);
    EnderecoResponseDTO findById(long id);
    List<EnderecoResponseDTO> findAll();


}
