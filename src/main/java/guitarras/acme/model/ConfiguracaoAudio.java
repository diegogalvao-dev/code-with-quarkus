package guitarras.acme.model;

import jakarta.persistence.*;

@Entity
public class ConfiguracaoAudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String tipoAmplificador;

    @Column(length = 100)
    private String presetEqualizador;

    @Column(nullable = false)
    private boolean temPedaleira;

    @OneToOne(mappedBy = "configuracaoAudio", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private  EstacaoTeste estacaoTeste;

    public EstacaoTeste getEstacaoTeste() {
        return estacaoTeste;
    }

    public void setEstacaoTeste(EstacaoTeste estacaoTeste) {
        this.estacaoTeste = estacaoTeste;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isTemPedaleira() {
        return temPedaleira;
    }

    public void setTemPedaleira(boolean temPedaleira) {
        this.temPedaleira = temPedaleira;
    }

    public String getTipoAmplificador() {
        return tipoAmplificador;
    }

    public void setTipoAmplificador(String tipoAmplificador) {
        this.tipoAmplificador = tipoAmplificador;
    }

    public String getPresetEqualizador() {
        return presetEqualizador;
    }

    public void setPresetEqualizador(String presetEqualizador) {
        this.presetEqualizador = presetEqualizador;
    }
}
