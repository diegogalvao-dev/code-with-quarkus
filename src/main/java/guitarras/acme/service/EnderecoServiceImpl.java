package guitarras.acme.service;

import guitarras.acme.dto.EnderecoDTO;
import guitarras.acme.dto.EnderecoResponseDTO;
import guitarras.acme.model.Endereco;
import guitarras.acme.model.Usuario;
import guitarras.acme.repository.EnderecoRepository;
import guitarras.acme.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService{

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    UsuarioRepository usuarioRepository;


    @Override
    @Transactional
    public EnderecoResponseDTO create(EnderecoDTO enderecoDTO) {

        Endereco novoEndereco = new Endereco();
        novoEndereco.setQuadra(enderecoDTO.quadra());

        Usuario user = usuarioRepository.findById(enderecoDTO.idUser());

        if (user == null) {
            throw new NotFoundException("Usuario com ID " + enderecoDTO.idUser() + " não encontrada para associação.");
        }

        novoEndereco.setUsuario(user);
        novoEndereco.setLote(enderecoDTO.lote());

        enderecoRepository.persist(novoEndereco);

        return EnderecoResponseDTO.valueOf(novoEndereco);
    }

    @Override
    @Transactional
    public void update(long id, EnderecoDTO enderecoDTO) {

        Endereco upEndereco = enderecoRepository.findById(id);

        if (upEndereco == null) {
            throw new NotFoundException("Endereço com ID " + id + " não encontrado para atualização.");
        }

        upEndereco.setQuadra(enderecoDTO.quadra());
        upEndereco.setLote(enderecoDTO.lote());

    }

    @Override
    @Transactional
    public void delete(long id) {

        Endereco delendereco = enderecoRepository.findById(id);

        if (delendereco == null) {
            throw new NotFoundException("Endereço com ID " + id + " não encontrado para deletar.");
        }

        enderecoRepository.delete(delendereco);

    }

    @Override
    public List<EnderecoResponseDTO> BuscarPorUser(Long idUser) {
        Usuario user = usuarioRepository.findById(idUser);

        if (user == null) {
            throw new NotFoundException("Usuario com ID " + idUser + " não encontrado para buscar.");
        }

        List<Endereco> enderecosDoUser = enderecoRepository.BuscarPorUser(idUser);
        return enderecosDoUser.stream().map(EnderecoResponseDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public EnderecoResponseDTO findById(long id) {
        Endereco endereco = enderecoRepository.findById(id);

        if (endereco == null) {
            throw new NotFoundException("Endereço com ID " + id + " não encontrado.");
        }

        return EnderecoResponseDTO.valueOf(endereco);
    }

    @Override
    public List<EnderecoResponseDTO> findAll() {
        return enderecoRepository.findAll().stream().map(EnderecoResponseDTO::valueOf).collect(Collectors.toList());
    }
}
