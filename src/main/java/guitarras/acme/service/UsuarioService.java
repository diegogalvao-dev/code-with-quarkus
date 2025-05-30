package guitarras.acme.service;

import guitarras.acme.dto.UsuarioResponseDTO;

public interface UsuarioService {

    UsuarioResponseDTO findByUsernameAndSenha(String username, String senha);

    UsuarioResponseDTO findByUsername(String username);

}
