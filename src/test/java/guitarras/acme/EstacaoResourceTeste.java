package guitarras.acme;


import guitarras.acme.dto.ConfiguracaoAudioDTO;
import guitarras.acme.dto.EstacaoTesteDTO;
import guitarras.acme.dto.EstacaoTesteResponseDTO;
import guitarras.acme.model.EstacaoTeste;
import guitarras.acme.service.EstacaoTesteService;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class EstacaoResourceTeste extends BaseTest{

    @Inject
    EstacaoTesteService estacaoService;

    private static final String ESTACAO_PATH = "/estacaoteste";
    private static final String CONFIGURACAO_PATH = "/configuracaoaudio";
    private static final String ESTACAO_BY_NAME_PATH = ESTACAO_PATH + "/{name}";
    private static final String ESTACAO_BY_ID_PATH = ESTACAO_PATH + "/{id}";

    @Test
    @TestTransaction
    void testBuscarTodos() {

        given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .when().get("/estacaoteste")
                .then()
                .statusCode(200);

    }

    @Test
    @TestTransaction
    void testApagar() {

        ConfiguracaoAudioDTO configDto = new ConfiguracaoAudioDTO("ToDeleteAmp", "Blues", false);
        Long idConfig = given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .contentType(ContentType.JSON)
                .body(configDto)
                .when().post(CONFIGURACAO_PATH)
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().jsonPath().getLong("id");
        assertThat("ConfiguracaoAudio ID for delete test not created", idConfig, notNullValue());

        EstacaoTesteDTO estacaoParaApagarDto = new EstacaoTesteDTO("ladoc", false, "local_apagar", idConfig);

        Long idEstacaoParaApagar = given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .contentType(ContentType.JSON)
                .body(estacaoParaApagarDto)
                .when().post(ESTACAO_PATH)
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().jsonPath().getLong("id");
        assertThat("EstacaoTeste ID for delete test not created", idEstacaoParaApagar, notNullValue());

        given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .pathParam("name", "ladoc")
                .when()
                .delete(ESTACAO_BY_NAME_PATH)
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

    }

    @Test
    @TestTransaction
    void testIncluir() {

        ConfiguracaoAudioDTO configDto = new ConfiguracaoAudioDTO("Marshall", "Rock", true);
        Long idConfiguracaoCriada = given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .contentType(ContentType.JSON)
                .body(configDto)
                .when().post(CONFIGURACAO_PATH)
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().jsonPath().getLong("id");

        assertThat("ID da ConfiguracaoAudio não foi criado", idConfiguracaoCriada, notNullValue());


        EstacaoTesteDTO estacaoteste = new EstacaoTesteDTO("lado b", true, "sala Acessorios", idConfiguracaoCriada);


        given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .contentType(ContentType.JSON)
                .body(estacaoteste)
                .when().post(ESTACAO_PATH)
                .then()
                .log().all()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .body("id", notNullValue(),
                        "name", is("lado b"),
                        "ocupada", is(true),
                        "localizacao", is("sala Acessorios"),

                        "configuracaoAudio.id", equalTo(idConfiguracaoCriada.intValue()))

                .body("configuracaoAudio.tipoAmplificador", is("Marshall"));
    }

    @Test
    @TestTransaction
    void buscarnaoOcupado(){

        ConfiguracaoAudioDTO configDto = new ConfiguracaoAudioDTO("Amp", "Jazz", false);
        Long idConfig = given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .contentType(ContentType.JSON).body(configDto)
                .when().post(CONFIGURACAO_PATH)
                .then().statusCode(201)
                .extract().jsonPath().getLong("id");
        assertThat("Config ID não foi criado para buscarnaoOcupado", idConfig, notNullValue());

        EstacaoTesteDTO estacaoNaoOcupadaDto = new EstacaoTesteDTO("ladoa", false, "local_NAO_ocupado", idConfig);
        Long idNaoOcupada = given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .contentType(ContentType.JSON)
                .body(estacaoNaoOcupadaDto)
                .when().post(ESTACAO_PATH)
                .then().statusCode(201)
                .extract().jsonPath().getLong("id");
        assertThat("ID da Estacao NÃO Ocupada não foi criado", idNaoOcupada, notNullValue());

        given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .when().get(ESTACAO_PATH + "/naoOcupada")
                .then()
                .log().all()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("size()", is(1))
                .body("[0].id", equalTo(idNaoOcupada.intValue()))
                .body("[0].name", is("ladoa"))
                .body("[0].ocupada", is(false))
                .body("[0].localizacao", is("local_NAO_ocupado"));
    }

    @Test
    @TestTransaction
    void testAlterar() {

        ConfiguracaoAudioDTO configDto = new ConfiguracaoAudioDTO("Amp", "Jazz", false);
        Long idConfig = given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .contentType(ContentType.JSON).body(configDto)
                .when().post(CONFIGURACAO_PATH)
                .then().statusCode(201)
                .extract().jsonPath().getLong("id");
        assertThat("Config ID não foi criado para buscarnaoOcupado", idConfig, notNullValue());

        EstacaoTesteDTO estacaoteste = new EstacaoTesteDTO("ladoh", false, "sala Acessorios", idConfig);
        Long idEstacao = given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .contentType(ContentType.JSON)
                .body(estacaoteste)
                .when().post(ESTACAO_PATH)
                .then()
                .log().all()
                .statusCode(201)
                .extract().jsonPath().getLong("id");

        EstacaoTesteDTO estacaotestealterado = new EstacaoTesteDTO("ladog", false, "otherside", idConfig);
        given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .contentType(ContentType.JSON)
                .body(estacaotestealterado)
                .pathParam("id", idEstacao)
                .when().put(ESTACAO_BY_ID_PATH)
                .then()
                .log().all()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());


        EstacaoTesteResponseDTO response = estacaoService.findById(idEstacao);
        assertThat(response.name(), is("ladog"));
        assertThat(response.ocupada(), is(false));
        assertThat(response.localizacao(), is("otherside"));
        assertThat("O objeto configuracaoAudio no DTO de resposta não deve ser nulo", response.configuracaoAudio(), notNullValue());
        assertThat("O ID da configuracaoAudio associada deve ser o esperado", response.configuracaoAudio().id(), is(idConfig));

    }

}
