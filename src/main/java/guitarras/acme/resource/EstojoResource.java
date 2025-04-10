package guitarras.acme.resource;


import guitarras.acme.dto.EstojoDTO;
import guitarras.acme.dto.EstojoResponseDTO;
import guitarras.acme.model.EstiloCase;
import guitarras.acme.service.EstojoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("estojo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstojoResource {

    @Inject
    EstojoService service;

    @GET
    public List<EstojoResponseDTO> buscarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/estiloCase/{id}")
    public List<EstojoResponseDTO> buscarporCase(@PathParam("id") String idEstiloCase) {
        EstiloCase estiloCase = EstiloCase.valueOf(idEstiloCase);
        return service.findByCase(estiloCase);
    }

    @POST
    public EstojoResponseDTO incluir(EstojoDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(Long id, EstojoDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(Long id) {
        service.delete(id);
    }

}
