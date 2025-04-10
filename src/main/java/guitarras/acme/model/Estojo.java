package guitarras.acme.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Estojo extends DefaultEntity{

    @Column(length = 60, nullable = false)
    private String material;

    private EstiloCase estiloCase;

    public EstiloCase getEstiloCase() {
        return estiloCase;
    }

    public void setEstiloCase(EstiloCase estiloCase) {
        this.estiloCase = estiloCase;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String tecido) {
        this.material = tecido;
    }



}
