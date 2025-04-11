package guitarras.acme.resource;


import guitarras.acme.dto.EstojoDTO;
import guitarras.acme.model.EstiloCase;
import guitarras.acme.service.EstojoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    @Path("/estiloCase/{id}")
    public Response buscarporCase(@PathParam("id") String idEstiloCase) {
        EstiloCase estiloCase = EstiloCase.valueOf(idEstiloCase);
        return Response.ok().entity(service.findByCase(estiloCase)).build();
    }

    @POST
    public Response incluir(EstojoDTO dto) {
        return Response.status(Status.CREATED).entity(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response alterar(Long id, EstojoDTO dto) {
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
