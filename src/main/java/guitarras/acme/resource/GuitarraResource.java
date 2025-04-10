package guitarras.acme.resource;


import guitarras.acme.dto.GuitarrasDTO;
import guitarras.acme.dto.GuitarrasResponseDTO;
import guitarras.acme.service.GuitarraService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("guitarra")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GuitarraResource {

    @Inject
    GuitarraService service;

    @GET
    public List<GuitarrasResponseDTO> buscarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/tipo/{tipo}")
    public List<GuitarrasResponseDTO> buscarPortipo(String tipo) {
        return service.findByTipo(tipo);
    }

    @GET
    @Path("/id/{id}")
    public GuitarrasResponseDTO buscarPorid(Long id) {
        return service.findById(id);
    }

    @POST
    public GuitarrasResponseDTO incluir(GuitarrasDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(Long id, GuitarrasDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(Long id) {
        service.delete(id);
    }

}
