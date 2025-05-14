package guitarras.acme.service;

import guitarras.acme.dto.PessoaFisicaDTO;
import guitarras.acme.dto.PessoaFisicaResponseDTO;

import java.util.List;

public interface PessoaFisicaService {

    PessoaFisicaResponseDTO create(PessoaFisicaDTO pessoaFisicaDTO);
    void update(long id, PessoaFisicaDTO pessoaFisicaDTO);
    void delete(long id);

    PessoaFisicaResponseDTO findById(long id);
    List<PessoaFisicaResponseDTO> findByName(String name);
    PessoaFisicaResponseDTO findByCpf(String cpf);
    List<PessoaFisicaResponseDTO> findAll();

}
