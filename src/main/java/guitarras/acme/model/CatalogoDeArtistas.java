package guitarras.acme.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class CatalogoDeArtistas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "artistaGuitarra", joinColumns = @JoinColumn(name = "idArtista"), inverseJoinColumns = @JoinColumn(name = "idGuitarra"))
    public List<Guitarra> guitarras;

    private EstiloMusical estiloMusical;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstiloMusical getEstiloMusical() {
        return estiloMusical;
    }

    public void setEstiloMusical(EstiloMusical estiloMusical) {
        this.estiloMusical = estiloMusical;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Guitarra> getGuitarras() {
        return guitarras;
    }

    public void setGuitarras(List<Guitarra> guitarras) {
        this.guitarras = guitarras;
    }

}
