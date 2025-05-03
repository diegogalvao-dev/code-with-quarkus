package guitarras.acme.resource;


import guitarras.acme.dto.EstacaoTesteDTO;
import guitarras.acme.dto.EstacaoTesteResponseDTO;
import guitarras.acme.model.EstacaoTeste;
import guitarras.acme.service.EstacaoTesteService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Path("/estacaoteste")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class EstacaoTesteResource {

    @Inject
    EstacaoTesteService service;

    @GET
    public Response buscarTodos() {
        return Response.ok().entity(service.findAll()).build();
    }

    @GET
    @Path("/naoOcupada")
    public Response buscarNaoOcupada(){
        return Response.ok().entity(service.findByNaoOcupada()).build();
    }

    @POST
    public Response incluir(@Valid EstacaoTesteDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("{id}")
    public Response alterar(@PathParam("id") Long id, @Valid EstacaoTesteDTO dto) {
        service.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{name}")
    public Response apagar(@PathParam("name") String name) {
        service.deleteByName(name);
        return Response.noContent().build();
    }

}
