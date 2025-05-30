package guitarras.acme.resource;

import guitarras.acme.dto.UsuarioResponseDTO;
import guitarras.acme.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

public class UsuarioResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService usuarioService;

    @GET
    @RolesAllowed({"User", "Adm"})
    @Path("/perfil")
    public Response buscarUsuarioLogado(){

        String username = jwt.getSubject();

        UsuarioResponseDTO usuarioResponseDTO = usuarioService.findByUsername(username);

        return Response.ok().entity(usuarioResponseDTO).build();

    }

}
