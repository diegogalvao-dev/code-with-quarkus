package guitarras.acme;

import guitarras.acme.dto.GuitarraAcusticaDTO;
import guitarras.acme.dto.GuitarraEletricaDTO;
import guitarras.acme.dto.GuitarrasDTO;
import guitarras.acme.dto.GuitarrasResponseDTO;
import guitarras.acme.service.GuitarraService;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;


import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertNull;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class GuitarraResourceTeste {

    @Inject
    GuitarraService guitarraService;

    private static final String GUITARRAS_PATH = "/guitarras";
    private static final String GUITARRAS_ELETRICA_PATH = GUITARRAS_PATH + "/eletrica";
    private static final String GUITARRAS_ACUSTICA_PATH = GUITARRAS_PATH + "/acustica";
    private static final String GUITARRAS_ID_PATH = GUITARRAS_PATH + "/{id}";
    private static final String GUITARRAS_SEARCH_PATH = GUITARRAS_PATH + "/search/{nome}";

    @Test
    public void testBuscarTodasGuitarras() {

        given()
                .when().get(GUITARRAS_PATH)
                .then()
                    .statusCode(Response.Status.OK.getStatusCode())
                    .body("size()", greaterThanOrEqualTo(0));

    }

    @Test
    public void testeBuscarPorNome() {

        given()
            .when().get(GUITARRAS_SEARCH_PATH, "Guitarra")
            .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("size()", greaterThanOrEqualTo(0));

    }

    @Test
    @TestTransaction
    public void testIncluirEletrica(){

        GuitarraEletricaDTO guitarraEletrica = new GuitarraEletricaDTO("fender", 1, 3, "Hardtail");

        given()
                .contentType(ContentType.JSON)
                .body(guitarraEletrica)
                .when().post(GUITARRAS_ELETRICA_PATH)
                .then()
                    .statusCode(Response.Status.CREATED.getStatusCode())
                    .body("id", notNullValue(), "nome", is("fender"), "tipo", is("ELETRICA"));


    }

    @Test
    @TestTransaction
    public void testIncluirAcustica(){

        GuitarraAcusticaDTO guitarraAcustica = new GuitarraAcusticaDTO("CG", 1, "teca", true, true);

        given()
                .contentType(ContentType.JSON)
                .body(guitarraAcustica)
                .when().post(GUITARRAS_ACUSTICA_PATH)
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .body("id", notNullValue(), "nome", is("CG"), "tipo", is("ACUSTICA"));

    }

    @Test
    @TestTransaction
    public void testAlterarGuitarra() {

        GuitarraEletricaDTO guitarraEletrica = new GuitarraEletricaDTO("fender", 1, 3, "Hardtail");
        Long idParaAlterar = given()
                .contentType(ContentType.JSON)
                .body(guitarraEletrica)
                .when()
                .post(GUITARRAS_ELETRICA_PATH)
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().jsonPath().getLong("id");

        GuitarrasDTO guitarraAlteradaDTO = new GuitarrasDTO("fender_ALTERADO", "ELETRICA", 2);

        given()
                .contentType(ContentType.JSON)
                .body(guitarraAlteradaDTO)
                .pathParam("id", idParaAlterar)
                .when()
                .put(GUITARRAS_ID_PATH)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())

                .body("id", equalTo(idParaAlterar.intValue()))
                .body("nome", equalTo("fender_ALTERADO"))
                .body("tipo", equalTo("ELETRICA"))
                .body("modelos.id", equalTo(2))
                .body("modelos.nome", equalTo("SG"));
    }


    @Test
    @TestTransaction
    public void testApagarGuitarra() {

        GuitarraAcusticaDTO guitarraParaApagar = new GuitarraAcusticaDTO("GuitarraParaApagar", 3, "mogno", false, false); // Model ID 3 = MARTINLX1E
        Long idParaApagar = given()
                .contentType(ContentType.JSON)
                .body(guitarraParaApagar)
                .when()
                .post(GUITARRAS_ACUSTICA_PATH)
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().jsonPath().getLong("id");

        assertThat("ID para apagar não deveria ser nulo", idParaApagar, notNullValue());

        given()
                .pathParam("id", idParaApagar)
                .when()
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

    }


}
