package guitarras.acme.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Modelos {
    STRATOCASTER(1, "Stratocaster"),
    SG(2, "SG"),
    MARTINLX1E(3, "Martin LX1E"),
    FENDERCD60S(4, "Fender CD-60s"),
    LESPAUL(5, "Les Paul");

    private final int ID;
    private final String NOME;

    Modelos(int id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public int getId() {
        return ID;
    }

    public String getNome() {
        return NOME;
    }

    public static Modelos valueOf(int id) {
        for (Modelos x : Modelos.values()) {
            if (x.getId() == id)
                return x;
        }
        return null;
    }

}