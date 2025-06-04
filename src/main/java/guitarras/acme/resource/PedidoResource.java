package guitarras.acme.resource;

import guitarras.acme.dto.PedidoResponseDTO;
import guitarras.acme.model.Pedido;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    PedidoService pedidoService;

    @POST
    @RolesAllowed({"User"})
    @Path("/criarpedido")
    public Response criarPedido(Pedido dto){

        String username = jwt.getSubject();

        PedidoResponseDTO pedido = pedidoService.create(dto, username);

        return Response.ok().entity(pedido).build();

    }

}
