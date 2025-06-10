package guitarras.acme.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido extends DefaultEntity{

    private Integer quantidade;
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "id_guitarra")
    private Guitarra guitarra;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    public Guitarra getGuitarra() {
        return guitarra;
    }

    public void setGuitarra(Guitarra guitarra) {
        this.guitarra = guitarra;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
