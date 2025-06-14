package guitarras.acme.service;

import guitarras.acme.dto.UsuarioDTO;
import guitarras.acme.dto.UsuarioResponseDTO;
import guitarras.acme.model.Perfil;
import guitarras.acme.model.PessoaFisica;
import guitarras.acme.model.Usuario;
import guitarras.acme.repository.PessoaFisicaRepository;
import guitarras.acme.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService{

    @Inject
    UsuarioRepository repository;

    @Inject
    PessoaFisicaRepository pessoaFisicaRepository;

    @Inject
    HashService hashService;

    @Override
    public UsuarioResponseDTO create(UsuarioDTO usuario) {

        Usuario novousuario = new Usuario();

        novousuario.setUsername(usuario.username());

        String hash = null;

        try {
            hash = hashService.getHashSenha(usuario.senha());
        } catch (Exception e){
            System.out.println(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
        }

        novousuario.setSenha(hash);

        novousuario.setPessoaFisica(pessoaFisicaRepository.findById(usuario.idpessoafisica()));
        novousuario.setPerfil(Perfil.valueOf(usuario.idperfil()));

        repository.persist(novousuario);

        return UsuarioResponseDTO.valueOf(novousuario);

    }

    @Override
    public void update(long id, UsuarioDTO dto) {

        Usuario alterarusuario = repository.findById(id);

        alterarusuario.setUsername(dto.username());

        String hash = null;

        try {
            hash = hashService.getHashSenha(dto.senha());
        } catch (Exception e){
            System.out.println(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
        }

        alterarusuario.setSenha(hash);

        alterarusuario.setPessoaFisica(pessoaFisicaRepository.findById(dto.idpessoafisica()));
        alterarusuario.setPerfil(Perfil.valueOf(dto.idperfil()));

    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO findById(long id) {
        return UsuarioResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        return repository.listAll().stream().map(UsuarioResponseDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO findByUsernameAndSenha(String username, String senha) {
        return UsuarioResponseDTO.valueOf(repository.findUserAndSenha(username, senha));
    }

    @Override
    public UsuarioResponseDTO findByUsername(String username) {
        return UsuarioResponseDTO.valueOf(repository.findByUsername(username));
    }
}
