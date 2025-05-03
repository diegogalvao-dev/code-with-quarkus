package guitarras.acme.resource;


import guitarras.acme.dto.CordaDTO;
import guitarras.acme.dto.CordaResponseDTO;
import guitarras.acme.service.CordaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

@Path("cordas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class CordaResource {

    @Inject
    CordaService service;

    @GET
    public Response buscarTodos() {
        return Response.ok().entity(service.findAll()).build();
    }

    @GET
    @Path("/calibre")
    public Response buscarporcalibre(@QueryParam("calibre") String calibre) {
        return Response.ok().entity(service.findByCalibre(calibre)).build();
    }

    @GET
    @Path("/guitarra/{idGuitarra}")
    public Response buscarPorGuitarra(@PathParam("idGuitarra") Long idGuitarra){
        List<CordaResponseDTO> lista = service.findByPorGuitarra(idGuitarra);
        return Response.ok(lista).build();
    }

    @POST
    public Response incluir(@Valid CordaDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(Long id, @Valid CordaDTO dto) {
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

    @DELETE
    @Path("/guitarra/{idGuitarra}")
    @Transactional
    public Response apagarPorGuitarra(@PathParam("idGuitarra") Long idGuitarra) {
        service.deleteByIdGuitarra(idGuitarra);
        return Response.noContent().build();
    }

}
