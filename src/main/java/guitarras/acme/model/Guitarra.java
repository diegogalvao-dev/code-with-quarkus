package guitarras.acme.model;

import jakarta.persistence.*;

@Entity
public class Guitarra extends DefaultEntity{

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false)
    private String tipo;

    private Modelos modelos;

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

