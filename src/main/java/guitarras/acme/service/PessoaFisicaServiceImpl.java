package guitarras.acme.service;

import guitarras.acme.dto.PessoaFisicaDTO;
import guitarras.acme.dto.PessoaFisicaResponseDTO;
import guitarras.acme.model.PessoaFisica;
import guitarras.acme.repository.PessoaFisicaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
@ApplicationScoped
public class PessoaFisicaServiceImpl implements PessoaFisicaService{

    @Inject
    PessoaFisicaRepository pessoaFisicaRepository;

    @Override
    @Transactional
    public PessoaFisicaResponseDTO create(PessoaFisicaDTO pessoaFisicaDTO){

        PessoaFisica novoPessoaFisica = new PessoaFisica();

        novoPessoaFisica.setName(pessoaFisicaDTO.nome());
        novoPessoaFisica.setCpf(pessoaFisicaDTO.cpf());

        pessoaFisicaRepository.persist(novoPessoaFisica);

        return PessoaFisicaResponseDTO.valueOf(novoPessoaFisica);

    }

    public void update(long id, PessoaFisicaDTO pessoaFisicaDTO){

        PessoaFisica alterarPessoaFisica = pessoaFisicaRepository.findById(id);

        alterarPessoaFisica.setName(pessoaFisicaDTO.nome());
        alterarPessoaFisica.setCpf(pessoaFisicaDTO.cpf());

    }

    public void delete(long id){
        pessoaFisicaRepository.deleteById(id);
    }

    public PessoaFisicaResponseDTO findById(long id){
        return PessoaFisicaResponseDTO.valueOf(pessoaFisicaRepository.findById(id));
    }

    public PessoaFisicaResponseDTO findByCpf(String cpf){
        return PessoaFisicaResponseDTO.valueOf(pessoaFisicaRepository.findByCpf(cpf));
    }

    public List<PessoaFisicaResponseDTO> findByName(String name){
        return pessoaFisicaRepository.findByName(name).stream().map(pf -> PessoaFisicaResponseDTO.valueOf(pf)).toList();
    }

    public List<PessoaFisicaResponseDTO> findAll(){
        return pessoaFisicaRepository.findAll().stream().map(a -> PessoaFisicaResponseDTO.valueOf(a)).toList();
    }
    
}
