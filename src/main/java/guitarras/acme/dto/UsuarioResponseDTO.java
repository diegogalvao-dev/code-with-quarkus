package guitarras.acme.dto;

import guitarras.acme.model.Perfil;
import guitarras.acme.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String name,
    String username,
    Perfil perfil) {

    public static UsuarioResponseDTO valueOf(Usuario usuario){

        if (usuario == null){
            return null;
        }

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getPessoaFisica().getName(),
                usuario.getUsername(),
                usuario.getPerfil());

    }

}
