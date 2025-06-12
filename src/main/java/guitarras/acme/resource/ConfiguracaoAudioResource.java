package guitarras.acme.resource;


import guitarras.acme.dto.ConfiguracaoAudioDTO;
import guitarras.acme.service.ConfiguracaoAudioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("configuracaoaudio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class ConfiguracaoAudioResource {

    @Inject
    ConfiguracaoAudioService service;

    @GET
    @RolesAllowed({"User", "Adm"})
    public Response buscarTodos() {
        return Response.ok().entity(service.findAll()).build();
    }

    @POST
    @RolesAllowed({"User", "Adm"})
    public Response incluir(@Valid ConfiguracaoAudioDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @RolesAllowed({"User", "Adm"})
    @Path("/{id}")
    public Response alterar(@PathParam("id") Long id, @Valid ConfiguracaoAudioDTO dto) {
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @RolesAllowed({"Adm"})
    @Path("/{id}")
    public Response apagar(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

}
