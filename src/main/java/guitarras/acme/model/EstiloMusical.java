package guitarras.acme.model;

public enum EstiloMusical {

    ROCK(1, "Rock"),
    COUNTRY(2, "Country"),
    HEAVYMETAL(3, "Heavy Metal"),
    JAZZ(4, "Jazz"),
    POP(5, "Pop");

    private final int ID;
    private final String NOME;

    EstiloMusical( int ID, String NOME) {
        this.NOME = NOME;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getNOME() {
        return NOME;
    }

    public static EstiloMusical valueOf(Integer id) {

        if (id == null){
            return null;
        }
        for (EstiloMusical x : EstiloMusical.values()) {
            if (x.getID() == id)
                return x;
        }
        return null;
    }

}
