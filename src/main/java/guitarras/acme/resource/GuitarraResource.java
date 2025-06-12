package guitarras.acme.resource;

import guitarras.acme.dto.*; // Importe os novos DTOs
import guitarras.acme.service.GuitarraServiceImpl; // Use a implementação
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Path("/guitarras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GuitarraResource {

    @Inject
    GuitarraServiceImpl service;

    @GET
    @RolesAllowed({"User", "Adm"})
    public Response buscarTodas() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @RolesAllowed({"User", "Adm"})
    @Path("/search/{nome}")
    public Response buscarPorNome(@PathParam("nome") String nome) {
        return Response.ok(service.findByNome(nome)).build();
    }


    @POST
    @RolesAllowed({"Adm"})
    @Path("/eletrica")
    @Transactional
    public Response incluirEletrica(@Valid GuitarraEletricaDTO dto) {
        try {
            GuitarrasResponseDTO guitarraCriada = service.createEletrica(dto);
            URI uri = UriBuilder.fromResource(GuitarraResource.class).path(guitarraCriada.id().toString()).build();
            return Response.created(uri).entity(guitarraCriada).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


    @POST
    @RolesAllowed({"Adm"})
    @Path("/acustica")
    @Transactional
    public Response incluirAcustica(@Valid GuitarraAcusticaDTO dto) {
        try {
            GuitarrasResponseDTO guitarraCriada = service.createAcustica(dto);
            URI uri = UriBuilder.fromResource(GuitarraResource.class).path(guitarraCriada.id().toString()).build();
            return Response.created(uri).entity(guitarraCriada).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @RolesAllowed({"Adm"})
    @Path("/{id}")
    @Transactional
    public Response alterar(@PathParam("id") Long id, @Valid GuitarrasDTO dto) {
        try {
            GuitarrasResponseDTO guitarraAtualizada = service.update(id, dto);
            return Response.ok(guitarraAtualizada).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @RolesAllowed({"Adm"})
    @Path("/{id}")
    @Transactional
    public Response apagar(@PathParam("id") Long id) {
        try {
            service.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}