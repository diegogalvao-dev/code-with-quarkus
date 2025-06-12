package guitarras.acme.resource;


import guitarras.acme.dto.CordaDTO;
import guitarras.acme.dto.CordaResponseDTO;
import guitarras.acme.service.CordaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

@Path("/cordas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CordaResource {

    @Inject
    CordaService service;

    @GET
    @RolesAllowed({"Adm"})
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            CordaResponseDTO dto = service.findById(id);
            return Response.ok(dto).build();
        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @RolesAllowed({"User", "Adm"})
    public Response buscarTodos() {
        return Response.ok().entity(service.findAll()).build();
    }


    @GET
    @RolesAllowed({"User"})
    @Path("/guitarra/{idGuitarra}")
    public Response buscarPorGuitarra(@PathParam("idGuitarra") Long idGuitarra){
        List<CordaResponseDTO> lista = service.findByPorGuitarra(idGuitarra);
        return Response.ok(lista).build();
    }

    @POST
    @RolesAllowed({"Adm"})
    public Response incluir(@Valid CordaDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @RolesAllowed({"Adm"})
    @Path("/{id}")
    public Response alterar(@PathParam("id") Long id, @Valid CordaDTO dto) {
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @RolesAllowed({"Adm"})
    @Path("/guitarra/{idGuitarra}")
    @Transactional
    public Response apagarPorGuitarra(@PathParam("idGuitarra") Long idGuitarra) {
        service.deleteByIdGuitarra(idGuitarra);
        return Response.noContent().build();
    }

}
