package guitarras.acme.resource;

import guitarras.acme.dto.EstojoDTO;
import guitarras.acme.dto.UsuarioDTO;
import guitarras.acme.dto.UsuarioResponseDTO;
import guitarras.acme.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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

    @GET
    @RolesAllowed({"Adm"})
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") long id){
        return Response.ok().entity(usuarioService.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Adm"})
    @Path("/search")
    public Response buscarTodos(){
        return Response.ok().entity(usuarioService.findAll()).build();
    }

    @GET
    @RolesAllowed({"Adm"})
    public Response buscarPorUsername(@QueryParam("username") String username){
        return Response.ok().entity(usuarioService.findByUsername(username)).build();
    }

    @POST
    @Transactional
    public Response criarUser(UsuarioDTO dto){
        return Response.status(Status.CREATED).entity(usuarioService.create(dto)).build();
    }

    @PUT
    @RolesAllowed({"Adm", "User"})
    @Transactional
    public Response alterar(@Valid UsuarioDTO dto) {

        String username = jwt.getSubject();

        UsuarioResponseDTO user = usuarioService.findByUsername(username);

        usuarioService.update(user.id(), dto);

        return Response.noContent().build();
    }

    @DELETE
    @RolesAllowed({"Adm", "User"})
    @Path("/{id}")
    @Transactional
    public Response apagar(Long id) {

        String username = jwt.getSubject();

        UsuarioResponseDTO user = usuarioService.findByUsername(username);

        usuarioService.delete(user.id());
        return Response.noContent().build();
    }


}
