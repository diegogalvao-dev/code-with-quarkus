package guitarras.acme.resource;


import guitarras.acme.dto.CatalogoDeArtistasDTO;
import guitarras.acme.dto.CordaDTO;
import guitarras.acme.model.CatalogoDeArtistas;
import guitarras.acme.service.CatalogoDeArtistasService;
import guitarras.acme.service.CordaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("catalogoDeArtistas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatalogoDeArtistasResource {

    @Inject
    CatalogoDeArtistasService service;

    @GET
    public Response buscarTodos() {
        return Response.ok().entity(service.findAll()).build();
    }

    @GET
    @Path("/cabibre/{calibre}")
    public Response buscarporcalibre(String name) {
        return Response.ok().entity(service.findByName(name)).build();
    }

    @POST
    @Path("/{id_artista}/guitarras/{id_guitarra}")
    public Response incluir(CatalogoDeArtistasDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(Long id, CatalogoDeArtistasDTO dto) {
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response apagar(Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

}
