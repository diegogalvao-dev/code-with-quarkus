package guitarras.acme.resource;


import guitarras.acme.dto.CordaDTO;
import guitarras.acme.dto.CordaResponseDTO;
import guitarras.acme.dto.CordaDTO;
import guitarras.acme.service.CordaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

@Path("corda")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CordaResource {

    @Inject
    CordaService service;

    @GET
    public Response buscarTodos() {
        return Response.ok().entity(service.findAll()).build();
    }

    @GET
    @Path("/cabibre/{calibre}")
    public Response buscarporcalibre(String calibre) {
        return Response.ok().entity(service.findByCalibre(calibre)).build();
    }

    @POST
    public Response incluir(CordaDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(Long id, CordaDTO dto) {
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
