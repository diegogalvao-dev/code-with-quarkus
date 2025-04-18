package guitarras.acme.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CatalogoDeArtistas extends DefaultEntity{

    @Column(length = 40, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "artista_guitarra", joinColumns = @JoinColumn(name = "id_artista"), inverseJoinColumns = @JoinColumn(name = "id_guitarra"))
    public List<Guitarra> guitarras = new ArrayList<>();

    private EstiloMusical estiloMusical;

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
