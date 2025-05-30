package guitarras.acme.resource;


import guitarras.acme.dto.EstojoDTO;
import guitarras.acme.model.EstiloCase;
import guitarras.acme.service.EstojoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


@Path("estojo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstojoResource {

    @Inject
    EstojoService service;

    @GET
    public Response buscarTodos() {
        return Response.ok().entity(service.findAll()).build();
    }

    @GET
    @Path("/material")
    public Response buscarPorMaterial(@QueryParam("material") String material) {
        return Response.ok().entity(service.findByMaterial(material)).build();
    }

    @GET
    @Path("/estiloCase/{nomeEstiloCase}")
    public Response buscarporCase(@PathParam("nomeEstiloCase") String nomeEstiloCase) {
        EstiloCase estilocase = EstiloCase.valueOf(nomeEstiloCase.toUpperCase());
        return Response.ok().entity(service.findByCase(estilocase)).build();
    }

    @POST
    public Response incluir(@Valid EstojoDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(Long id, @Valid EstojoDTO dto) {
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
