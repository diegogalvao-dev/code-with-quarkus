package guitarras.acme.resource;

import guitarras.acme.dto.PessoaFisicaDTO;
import guitarras.acme.model.PessoaFisica;
import guitarras.acme.service.PessoaFisicaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("pessoasfisica")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaFisicaResource {

    @Inject
    PessoaFisicaService service;

    @GET
    @RolesAllowed({"Adm"})
    public Response buscarTodos(){
        return Response.ok().entity(service.findAll()).build();
    }

    @GET
    @RolesAllowed({"Adm"})
    @Path("/name/{name}")
    public Response buscarPorName(String name){
        return Response.ok().entity(service.findByName(name)).build();
    }

    @GET
    @RolesAllowed({"Adm"})
    @Path("/cpf/{cpf}")
    public Response buscarPorCpf(String cpf){
        return Response.ok().entity(service.findByCpf(cpf)).build();
    }

    @GET
    @RolesAllowed({"Adm"})
    @Path("/{id}")
    public Response buscarPorCpf(Long id) {
        return Response.ok().entity(service.findById(id)).build();
    }

    @POST
    @RolesAllowed({"Adm"})
    public Response incluir(@Valid PessoaFisicaDTO dto){
        return Response.status(Response.Status.CREATED).entity(service.create(dto)).build();
    }

    @DELETE
    @RolesAllowed({"Adm"})
    @Path("/{id}")
    @Transactional
    public Response apagar(Long id){
        service.delete(id);
        return Response.noContent().build();
    }

}
