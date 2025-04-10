package guitarras.acme.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.yaml.snakeyaml.events.Event;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EstiloCase {

    BAG(1, "Capa Mole"),
    SEMICASE(2, "Capa Semi-rigida"),
    HARDCASE(3, "estojo rigido"),
    FLIGHTCASE(4, "reforço com aluminio"),
    MOLDADO(5, "formato da guitarra");

    private final int ID;
    private final String NOME;

    EstiloCase(int id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public int getId() {
        return ID;
    }

    public String getNome() {
        return NOME;
    }

    public static EstiloCase valueOf(Integer id) {

        if (id == null){
            return null;
        }
        for (EstiloCase x : EstiloCase.values()) {
            if (x.getId() == id)
                return x;
        }
        return null;
    }

}
