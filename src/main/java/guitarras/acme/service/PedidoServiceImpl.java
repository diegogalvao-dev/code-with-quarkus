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
import jakarta.ws.rs.Path;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return pedidoRepository.findByUsuario(username).stream().map(PedidoResponseDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public PedidoResponseDTO findById(long idPedido) {
        return PedidoResponseDTO.valueOf(pedidoRepository.findById(idPedido));
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
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);

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

    @Override
    @Transactional
    public void delete(long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public PedidoResponseDTO ProcessamentoPagamento(Long idpedido, boolean aprovado) {
        Pedido pedido = pedidoRepository.findByIdOptional(idpedido)
                .orElseThrow(() -> new NotFoundException("Pedido com ID " + idpedido + " não encontrado."));

        // Verifica se o pedido está aguardando pagamento
        if (pedido.getStatus() != StatusPedido.AGUARDANDO_PAGAMENTO) {
            throw new IllegalStateException("Este pedido não está aguardando pagamento. Status atual: " + pedido.getStatus());
        }

        if (aprovado) {
            pedido.setStatus(StatusPedido.PAGAMENTO_APROVADO);
            // Aqui você poderia, por exemplo, mudar para EM_PROCESSAMENTO se quisesse
            // pedido.setStatus(StatusPedido.EM_PROCESSAMENTO);
            System.out.println("LOG: Pagamento APROVADO para o pedido ID: " + idpedido);
        } else {
            pedido.setStatus(StatusPedido.PAGAMENTO_RECUSADO);
            System.out.println("LOG: Pagamento RECUSADO para o pedido ID: " + idpedido);
            // Consideração para um sistema real:
            // Se o pagamento for recusado, você pode precisar reverter a baixa de estoque.
            for(ItemPedido item : pedido.getItens()) {
                Guitarra guitarra = item.getGuitarra();
                guitarra.setEstoque(guitarra.getEstoque() + item.getQuantidade());
                guitarraRepository.persist(guitarra); // Panache gerencia
            }
            // Para o trabalho, apenas mudar o status pode ser suficiente, mas mencione essa lógica.
        }
        pedidoRepository.persist(pedido); // Não é necessário chamar persist explicitamente se a entidade for gerenciada e modificada dentro de uma transação.
        return PedidoResponseDTO.valueOf(pedido);
    }

}
