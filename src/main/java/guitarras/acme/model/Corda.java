package guitarras.acme.model;

import jakarta.persistence.*;

@Entity
public class Corda extends DefaultEntity{

    @Column(length = 20)
    private String calibre;

    @ManyToOne
    @JoinColumn(name = "id_guitarra")
    private Guitarra guitarra;

    public String getCalibre() {
        return calibre;
    }

    public void setCalibre(String calibre) {
        this.calibre = calibre;
    }

    public Guitarra getGuitarra() {
        return guitarra;
    }

    public void setGuitarra(Guitarra guitarra) {
        this.guitarra = guitarra;
    }
}
