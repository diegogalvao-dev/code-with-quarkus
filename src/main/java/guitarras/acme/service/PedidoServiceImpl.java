package guitarras.acme.service;

import guitarras.acme.dto.ItemPedidoDTO;
import guitarras.acme.dto.PedidoDTO;
import guitarras.acme.dto.PedidoResponseDTO;
import guitarras.acme.model.*;
import guitarras.acme.repository.EnderecoRepository;
import guitarras.acme.repository.GuitarraRepository;
import guitarras.acme.repository.PedidoRepository;
import guitarras.acme.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

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

    @Inject
    EnderecoRepository enderecoRepository;


    @Override
    public List<PedidoResponseDTO> findByUsername(String username) {
        //falta implementar
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }

    @Override
    public PedidoResponseDTO findById(long idPedido, String username) {
        //falta implementar
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    @Transactional
    public PedidoResponseDTO create(PedidoDTO pedidoDTO, String username) {

        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new NotFoundException("Usuário com username '" + username + "' não encontrado.");
        }

        List<Endereco> endereco = enderecoRepository.BuscarPorUser(usuario.getId());
        Endereco selecaoEndereco = endereco.stream().filter(e -> e.getId().equals(pedidoDTO.idEndereco())).findFirst().orElse(null);

        if (selecaoEndereco == null) {
            throw new NotFoundException("endereço, " + selecaoEndereco + "' não encontrado.");
        }

        Pedido pedido = new Pedido();
        pedido.setDataHora(LocalDateTime.now());
        pedido.setUsuario(usuario);
        pedido.setEndereco(selecaoEndereco);

        List<ItemPedido> listaItens = new ArrayList<>();
        double totalCalculado = 0.0;

        for(ItemPedidoDTO itemDTO : pedidoDTO.itens()){

            Guitarra guitarra = guitarraRepository.findById(itemDTO.idProduto());

            if (guitarra.getEstoque() < itemDTO.qtd()) {
                throw new IllegalStateException("Estoque insuficiente: " + guitarra.getNome() + ", Disponível: " + guitarra.getEstoque());
            }

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setGuitarra(guitarra);

            item.setPreco(item.getGuitarra().getPrice());
            item.setQuantidade(itemDTO.qtd());

            listaItens.add(item);

            totalCalculado += (guitarra.getPrice() * itemDTO.qtd());

            //alterando o estoque
            guitarra.setEstoque(guitarra.getEstoque() - itemDTO.qtd());

        }

        if (Math.abs(totalCalculado - pedidoDTO.total()) > 0.01) { // Comparação de doubles com tolerância
                 throw new IllegalArgumentException("O total do pedido calculado (" + totalCalculado + ") não confere com o total informado (" + pedidoDTO.total() + ").");
        }

        pedido.setTotalPedido(totalCalculado);
        pedido.setItens(listaItens);

        pedidoRepository.persist(pedido);

        return PedidoResponseDTO.valueOf(pedido);

    }
}
