package guitarras.acme.service;

import guitarras.acme.dto.ItemPedidoDTO;
import guitarras.acme.dto.PedidoDTO;
import guitarras.acme.dto.PedidoResponseDTO;
import guitarras.acme.model.Guitarra;
import guitarras.acme.model.ItemPedido;
import guitarras.acme.model.Pedido;
import guitarras.acme.model.Usuario;
import guitarras.acme.repository.GuitarraRepository;
import guitarras.acme.repository.PedidoRepository;
import guitarras.acme.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService{

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    GuitarraRepository guitarraRepository;


    @Override
    public List<PedidoResponseDTO> findByUsername(String username) {
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }

    @Override
    public PedidoResponseDTO findById(long idPedido, String username) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    @Transactional
    public PedidoResponseDTO create(PedidoDTO pedidoDTO, String username) {

        Usuario usuario = usuarioRepository.findByUsername(username);

        Pedido pedido = new Pedido();
        pedido.setDataHora(LocalDateTime.now());

        pedido.setTotalPedido(pedidoDTO.total());
        pedido.setUsuario(usuario);

        List<ItemPedido> listaItem = new ArrayList<ItemPedido>();

        for(ItemPedidoDTO itemDTO : pedidoDTO.itens()){

            Guitarra guitarra = guitarraRepository.findById(itemDTO.idProduto());

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setGuitarra(guitarra);

            //verificar se o preco do dto é o mesmo do produto
            item.setPreco(item.getGuitarra().getPreco());
            item.setQuantidade(itemDTO.qtd());

            listaItem.add(item);

            //alterando o estoque
            guitarra.setEstoque(guitarra.getEstoque() - itemDTO.qtd());

        }

        pedido.setItens(listaItem);

        pedidoRepository.persist(pedido);

        return PedidoResponseDTO.valueOf(pedido);

    }
}
