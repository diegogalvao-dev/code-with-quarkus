package guitarras.acme.model;

import jakarta.persistence.Entity;

@Entity
public class PessoaFisica extends Pessoa{

    private String cpg;

    public String getCpg() {
        return cpg;
    }

    public void setCpg(String cpg) {
        this.cpg = cpg;
    }

}
