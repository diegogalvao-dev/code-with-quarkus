package guitarras.acme.dto;

public final class GuitarrasDTO {

    private final String nome;
    private final String tipo;
    private final long idModelo;

    public GuitarrasDTO(String nome, String sigla, long idModelo) {
        this.nome = nome;
        this.tipo = sigla;
        this.idModelo = idModelo;
    }

    public long getIdModelo() {
        return idModelo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

}