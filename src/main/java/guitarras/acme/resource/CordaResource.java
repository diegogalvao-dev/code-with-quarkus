package guitarras.acme.resource;


import guitarras.acme.dto.CordaDTO;
import guitarras.acme.dto.CordaResponseDTO;
import guitarras.acme.dto.CordaDTO;
import guitarras.acme.service.CordaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("corda")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CordaResource {

    @Inject
    CordaService service;

    @GET
    public List<CordaResponseDTO> buscarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/cabibre/{calibre}")
    public CordaResponseDTO buscarPorid(Long id) {
        return service.findById(id);
    }

    @POST
    public CordaResponseDTO incluir(CordaDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(Long id, CordaDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(Long id) {
        service.delete(id);
    }

}
