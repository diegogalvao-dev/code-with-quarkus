package guitarras.acme.dto;

public record UsuarioDTO(

        String username,

        String senha,

        Long idpessoafisica,

        Integer idperfil) {

}
