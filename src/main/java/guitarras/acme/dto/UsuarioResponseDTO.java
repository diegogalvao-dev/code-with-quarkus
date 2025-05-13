package guitarras.acme.dto;

import guitarras.acme.model.Perfil;
import guitarras.acme.model.Usuario;

public record UsuarioResponseDTO(Long id, String username, String senha, Perfil perfil) {

    public static UsuarioResponseDTO valueOf(Usuario usuario){

        if (usuario == null){
            return null;
        }

        return new UsuarioResponseDTO(usuario.getId(), usuario.getUsername(), usuario.getSenha(), usuario.getPerfil());

    }

}
