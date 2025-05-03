package guitarras.acme.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ACUSTICA")
public class GuitarraAcustica extends Guitarra {

    @Column(length = 50)
    private String tipoMadeiraTampo;

    private Boolean possuiCutaway;

    private Boolean eletroacustica;


    public GuitarraAcustica() {
        super();
    }

    public String getTipoMadeiraTampo() {
        return tipoMadeiraTampo;
    }

    public void setTipoMadeiraTampo(String tipoMadeiraTampo) {
        this.tipoMadeiraTampo = tipoMadeiraTampo;
    }

    public Boolean getPossuiCutaway() {
        return possuiCutaway;
    }

    public void setPossuiCutaway(Boolean possuiCutaway) {
        this.possuiCutaway = possuiCutaway;
    }

    public Boolean getEletroacustica() {
        return eletroacustica;
    }

    public void setEletroacustica(Boolean eletroacustica) {
        this.eletroacustica = eletroacustica;
    }
}