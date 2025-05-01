package guitarras.acme.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estacao_teste")
public class EstacaoTeste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private boolean ocupada;

    @Column(length = 50, nullable = false)
    private String localizacao;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_configuracao_audio", referencedColumnName = "id", unique = true)
    private ConfiguracaoAudio configuracaoAudio;

    public ConfiguracaoAudio getConfiguracaoAudio() {
        return configuracaoAudio;
    }

    public void setConfiguracaoAudio(ConfiguracaoAudio configuracaoAudio) {
        this.configuracaoAudio = configuracaoAudio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}
