package guitarras.acme.resource;


import guitarras.acme.dto.GuitarrasDTO;
import guitarras.acme.dto.GuitarrasResponseDTO;
import guitarras.acme.service.GuitarraService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("guitarra")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GuitarraResource {

    @Inject
    GuitarraService service;

    @GET
    public Response buscarTodos() {
        return Response.ok().entity(service.findAll()).build();
    }

    @GET
    @Path("/tipo/{tipo}")
    public Response buscarPortipo(String tipo) {
        return Response.ok().entity(service.findByTipo(tipo)).build();
    }

    @GET
    @Path("/id/{id}")
    public Response buscarPorid(Long id) {
        return Response.ok().entity(service.findById(id)).build();
    }

    @POST
    public Response incluir(GuitarrasDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(Long id, GuitarrasDTO dto) {
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
