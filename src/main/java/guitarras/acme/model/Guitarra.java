package guitarras.acme.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Guitarra extends DefaultEntity{

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false)
    private String tipo;

    private Modelos modelos;

    @ManyToMany(mappedBy = "guitarras")
    public List<CatalogoDeArtistas> artistas = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Modelos getModelos() {
        return modelos;
    }

    public void setModelos(Modelos modelos) {
        this.modelos = modelos;
    }

}

