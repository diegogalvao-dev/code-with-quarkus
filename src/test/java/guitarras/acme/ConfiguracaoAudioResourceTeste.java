package guitarras.acme;

import static org.hamcrest.CoreMatchers.is;
import guitarras.acme.dto.ConfiguracaoAudioDTO;
import guitarras.acme.dto.ConfiguracaoAudioResponseDTO;
import guitarras.acme.service.ConfiguracaoAudioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class ConfiguracaoAudioResourceTeste {

    @Inject
    ConfiguracaoAudioService CService;

    @Test
    void testeBuscarTodos() {
        given()
                .when().get("/configuracaoaudio")
                .then()
                .statusCode(200);
    }

    @Test
    void testeConfiguracaoIncluir(){

        ConfiguracaoAudioDTO configDto = new ConfiguracaoAudioDTO("Marshall", "Rock", true);

        given()
                .contentType(ContentType.JSON)
                .body(configDto)
                .when().post("/configuracaoaudio")
                .then()
                .statusCode(201)
                .body("id", notNullValue(), "tipoAmplificador", is("Marshall"), "presetEqualizador", is("Rock"), "temPedaleira", is(true));

    }

    static Long id = null;

    @Test
    void testConfiguracaoAlterar() {
        ConfiguracaoAudioDTO configuracao = new ConfiguracaoAudioDTO("xln", "gg", true);

        id =  CService.create(configuracao).id();

        ConfiguracaoAudioDTO estadoAlterado = new ConfiguracaoAudioDTO("ght", "rr", false);

        given()
                .contentType(ContentType.JSON)
                .body(estadoAlterado)
                .when().put("/configuracaoaudio/" + id)
                .then()
                .statusCode(204);

        ConfiguracaoAudioResponseDTO response = CService.findById(id);
        assertThat(response.presetEqualizador(), is("rr"));
        assertThat(response.tipoAmplificador(), is("ght"));
        assertThat(response.temPedaleira(), is(false));
    }

    @Test
    void testConfiguracaoApagar() {
        given()
                .when().delete("/configuracaoaudio/" + id)
                .then()
                .statusCode(204);

        ConfiguracaoAudioResponseDTO response = CService.findById(id);
        assertNull(response);

    }


}
