package guitarras.acme.resource;


import guitarras.acme.dto.GuitarrasDTO;
import guitarras.acme.model.Guitarra;
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
    public List<Guitarra> buscarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/tipo/{tipo}")
    public Guitarra buscarPortipo(String tipo) {
        return service.findByTipo(tipo);
    }

    @GET
    @Path("/id/{id}")
    public Guitarra buscarPorid(Long id) {
        return service.findById(id);
    }

    @POST
    public Guitarra incluir(GuitarrasDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(Long id, Guitarra guitarra) {
        service.update(id, guitarra);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(Long id) {
        service.delete(id);
    }

}
