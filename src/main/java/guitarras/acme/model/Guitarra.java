package guitarras.acme.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_GUITARRA", discriminatorType = DiscriminatorType.STRING)
public class Guitarra extends DefaultEntity {

    @Column(length = 60, nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Modelos modelos;

    @ManyToMany(mappedBy = "guitarras", fetch = FetchType.LAZY)
    private List<CatalogoDeArtistas> artistas = new ArrayList<>();

    @Column(columnDefinition = "INT CHECK (estoque >= 0)")// @Check(constraints = "estoque >= 0")
    private Integer estoque;

    @Column(nullable = false)
    private Double price;

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Modelos getModelos() {
        return modelos;
    }

    public void setModelos(Modelos modelos) {
        this.modelos = modelos;
    }

    public List<CatalogoDeArtistas> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<CatalogoDeArtistas> artistas) {
        this.artistas = artistas;
    }

    public Guitarra() {}
}

