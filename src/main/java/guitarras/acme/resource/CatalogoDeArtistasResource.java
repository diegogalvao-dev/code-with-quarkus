package guitarras.acme.resource;


import guitarras.acme.dto.CatalogoDeArtistasDTO;
import guitarras.acme.dto.CatalogoDeArtistasResponseDTO;
import guitarras.acme.dto.CordaDTO;
import guitarras.acme.model.CatalogoDeArtistas;
import guitarras.acme.model.Guitarra;
import guitarras.acme.service.CatalogoDeArtistasService;
import guitarras.acme.service.CordaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Path("catalogoDeArtistas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class CatalogoDeArtistasResource {

    @Inject
    CatalogoDeArtistasService service;

    @GET
    public Response buscarTodos() {
        return Response.ok().entity(service.findAll()).build();
    }

    @POST
    public Response incluir(CatalogoDeArtistasDTO dto) {
//        return Response.status(Status.CREATED).entity(service.create(dto)).build();
        CatalogoDeArtistas novoArtista = service.create(dto);
        CatalogoDeArtistasResponseDTO artistaResponse = service.findById(novoArtista.getId());
        URI uri = UriBuilder.fromResource(CatalogoDeArtistasResource.class).path(novoArtista.getId().toString()).build();
        return Response.created(uri).entity(artistaResponse).build();
    }

    @POST
    @Path("/{idArtista}/guitarras/{idGuitarra}")
    public Response adicionarGuitarra(@PathParam("idArtista") Long idArtista, @PathParam("idGuitarra") Long idGuitarra){
        CatalogoDeArtistas artistasGuitarras = service.addGuitarra(idArtista, idGuitarra);

        if (artistasGuitarras == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Artista ou Guitarra não encontrado.").build();
        }

        CatalogoDeArtistasResponseDTO artistasResponse = service.findById(artistasGuitarras.getId());
        return Response.ok(artistasResponse).build();

    }

    @PUT
    @Path("/{id}")
    public Response alterar(@PathParam("id") Long id, CatalogoDeArtistasDTO dto) {
//        service.update(id, dto);
        CatalogoDeArtistas artistaAtualizado = service.update(id, dto);

        if (artistaAtualizado == null) {
            return Response.noContent().build();
        }

        CatalogoDeArtistasResponseDTO artistaResponse = service.findById(artistaAtualizado.getId());
        return Response.ok(artistaResponse).build();
    }

    @DELETE
    @Path("/{id}")
    public Response apagar(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

}
