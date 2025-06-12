package guitarras.acme.model;


import jakarta.persistence.*;

@Entity
@Table(name = "endereco", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"quadra", "lote", "id_usuario"})
})
public class Endereco extends DefaultEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String quadra;

    @Column(nullable = false)
    private Integer lote;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getLote() {
        return lote;
    }

    public void setLote(Integer lote) {
        this.lote = lote;
    }

    public String getQuadra() {
        return quadra;
    }

    public void setQuadra(String quadra) {
        this.quadra = quadra;
    }
}
