package guitarras.acme.service;

import guitarras.acme.dto.UsuarioDTO;
import guitarras.acme.dto.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioResponseDTO create(UsuarioDTO usuario);
    void update(long id, UsuarioDTO usuario);
    void delete(long id);

    UsuarioResponseDTO findById(long id);
    List<UsuarioResponseDTO> findAll();
    UsuarioResponseDTO findByUsernameAndSenha(String username, String senha);
    UsuarioResponseDTO findByUsername(String username);

}
