package guitarras.acme.dto;

import guitarras.acme.model.ItemPedido;

public record ItemPedidoResponseDTO(Long idProduto, Integer qtd, Double preco) {

    public static ItemPedidoResponseDTO valueOf(ItemPedido itemPedido){
        return new ItemPedidoResponseDTO(itemPedido.getGuitarra().getId(), itemPedido.getQuantidade(), itemPedido.getPreco());
    }

}
