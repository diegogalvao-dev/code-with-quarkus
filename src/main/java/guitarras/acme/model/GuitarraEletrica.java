package guitarras.acme.model;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ELETRICA")
public class GuitarraEletrica extends Guitarra {

    @Column(name = "numero_captadores")
    private Integer numeroCaptadores;

    @Column(length = 50)
    private String tipoPonte;

    public GuitarraEletrica() {
        super();
    }

    public Integer getNumeroCaptadores() {
        return numeroCaptadores;
    }

    public void setNumeroCaptadores(Integer numeroCaptadores) {
        this.numeroCaptadores = numeroCaptadores;
    }

    public String getTipoPonte() {
        return tipoPonte;
    }

    public void setTipoPonte(String tipoPonte) {
        this.tipoPonte = tipoPonte;
    }
}