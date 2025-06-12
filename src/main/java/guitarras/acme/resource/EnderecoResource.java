package guitarras.acme.resource;

import guitarras.acme.dto.EnderecoDTO;
import guitarras.acme.service.EnderecoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/endereco")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService enderecoService;

    @GET
    @RolesAllowed({"User", "Adm"})
    public Response buscarTodos() {
        return Response.ok().entity(enderecoService.findAll()).build();
    }

    @GET
    @RolesAllowed({"User", "Adm"})
    @Path("/usuario/{id}")
    public Response BuscarPorUsuario(Long id){
        return Response.ok().entity(enderecoService.BuscarPorUser(id)).build();
    }

    @POST
    @RolesAllowed({"User"})
    public Response criar(EnderecoDTO dto){
        return Response.status(Response.Status.CREATED).entity(enderecoService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"User"})
    public Response Atualizar(@PathParam("id") Long id, EnderecoDTO dto){
        enderecoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"User"})
    public Response Deletar(@PathParam("id") Long id){
        enderecoService.delete(id);
        return Response.noContent().build();
    }





}
