package guitarras.acme.service;

import guitarras.acme.dto.UsuarioResponseDTO;
import guitarras.acme.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService{

    @Inject
    UsuarioRepository repository;

    @Override
    public UsuarioResponseDTO findByUsernameAndSenha(String username, String senha) {
        return UsuarioResponseDTO.valueOf(repository.findUserAndSenha(username, senha));
    }

    @Override
    public UsuarioResponseDTO findByUsername(String username) {
        return UsuarioResponseDTO.valueOf(repository.findByUsername(username));
    }
}
