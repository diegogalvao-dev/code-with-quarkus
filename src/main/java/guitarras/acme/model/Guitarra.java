package guitarras.acme.model;


import guitarras.acme.model.DefaultEntity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Estratégia de Herança
@DiscriminatorColumn(name = "TIPO_GUITARRA", discriminatorType = DiscriminatorType.STRING) // Coluna para diferenciar
public class Guitarra extends DefaultEntity { // Herda de DefaultEntity (ótimo!)

    @Column(length = 60, nullable = false)
    private String nome;

    // REMOVIDO: O campo 'tipo' não é mais necessário aqui.
    // @Column(length = 20, nullable = false)
    // private String tipo;

    @Enumerated(EnumType.STRING) // Boa prática mapear Enum como String
    @Column(nullable = false) // Assumindo que toda guitarra tem um modelo
    private Modelos modelos;

    // Boa prática definir FetchType explicitamente
    @ManyToMany(mappedBy = "guitarras", fetch = FetchType.LAZY)
    private List<CatalogoDeArtistas> artistas = new ArrayList<>(); // Inicializar a lista

    // --- Getters e Setters ---

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para 'tipo' removidos

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

    // Não remova construtor padrão se DefaultEntity não tiver um acessível
    public Guitarra() {}
}

