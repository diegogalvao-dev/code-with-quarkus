package guitarras.acme.resource;

import guitarras.acme.dto.PedidoDTO;
import guitarras.acme.dto.PedidoResponseDTO;
import guitarras.acme.model.Pedido;
import guitarras.acme.service.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.hibernate.engine.spi.Status;

@Path("pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    PedidoService pedidoService;

    @GET
    @RolesAllowed({"User", "Adm"})
    public Response BuscarPorUsername(){

        String username = jwt.getSubject();

        return Response.ok().entity(pedidoService.findByUsername(username)).build();

    }

    @GET
    @RolesAllowed({"User", "Adm"})
    @Path("/{id}")
    public Response BuscarPorId(@PathParam("id") Long id){
        return Response.ok().entity(pedidoService.findById(id)).build();
    }


    @POST
    @RolesAllowed({"User"})
    @Path("/criarpedido")
    public Response criarPedido(PedidoDTO dto){

        String username = jwt.getSubject();

        PedidoResponseDTO pedido = pedidoService.create(dto, username);

        return Response.ok().entity(pedido).build();

    }

    @POST
    @Path("/{id}/simular-pagamento")
    @RolesAllowed({"User"})
    public Response simularPagamento(@PathParam("id") Long pedidoId, @QueryParam("aprovado") boolean aprovado) {

        String usernameLogado = jwt.getSubject();
        PedidoResponseDTO pedidoAntes = pedidoService.findById(pedidoId);

        if (pedidoAntes == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Pedido não encontrado.").build();
        }
//        // Verifica se o usuário logado é o dono do pedido
//        if (pedidoAntes.nomeUsuario() == null || !pedidoAntes.nomeUsuario().equals(usernameLogado)) {
//            return Response.status(Response.Status.FORBIDDEN).entity("Você não tem permissão para processar o pagamento deste pedido.").build();
//        }

        try {
            PedidoResponseDTO pedidoAtualizado = pedidoService.ProcessamentoPagamento(pedidoId, aprovado);
            return Response.ok(pedidoAtualizado).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalStateException e) {
            // IllegalStateException pode ser por status inválido do pedido
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }


    @DELETE
    @RolesAllowed({"User", "Adm"})
    @Path("/{id}")
    public Response apagar(@PathParam("id") long id){
        pedidoService.delete(id);
        return Response.noContent().build();
    }



}
