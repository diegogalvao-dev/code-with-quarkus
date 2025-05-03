package guitarras.acme;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import guitarras.acme.dto.*;
import guitarras.acme.service.CordaService;
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
public class CordaResourceTeste {

    @Inject
    CordaService cordaService;
    private static final String GUITARRAS_PATH = "/guitarras";
    private static final String GUITARRAS_ELETRICA_PATH = GUITARRAS_PATH + "/eletrica";
    private static final String CORDA_PATH = "/cordas";
    private static final String GUITARRA_PATH = "/guitarras"; // Confirme se este path está correto


    @Test
    void testBuscarTodos() {
        given()
                .when().get("/cordas")
                .then()
                .statusCode(200);
    }

    @Test
    void testIncluir(){

        GuitarraEletricaDTO guitarraEletrica = new GuitarraEletricaDTO("fender", 1, 3, "Hardtail");
        Long idParaAlterar = given()
                .contentType(ContentType.JSON)
                .body(guitarraEletrica)
                .when()
                .post(GUITARRAS_ELETRICA_PATH)
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().jsonPath().getLong("id");

        CordaDTO corda = new CordaDTO("gg", idParaAlterar);
        given()
                .contentType(ContentType.JSON)
                .body(corda)
                .when().post("/cordas")
                .then()
                .statusCode(201)
                .body("id", notNullValue(), "calibre", is("gg"), "guitarra.id", equalTo(idParaAlterar.intValue()));


    }

    static Long id = null;

    @Test
    void testAlterar(){

        GuitarraEletricaDTO guitarraEletrica = new GuitarraEletricaDTO("fender", 1, 3, "Hardtail");
        Long idParaAlterar = given()
                .contentType(ContentType.JSON)
                .body(guitarraEletrica)
                .when()
                .post(GUITARRAS_ELETRICA_PATH)
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().jsonPath().getLong("id");

        CordaDTO corda = new CordaDTO("gg", idParaAlterar);
        given()
                .contentType(ContentType.JSON)
                .body(corda)
                .when().post("/cordas")
                .then()
                .statusCode(201)
                .body("id", notNullValue(), "calibre", is("gg"), "guitarra.id", equalTo(idParaAlterar.intValue()));

        id =  cordaService.create(corda).id();

        CordaDTO cordaAlterado = new CordaDTO("jj", idParaAlterar);
        given()
                .contentType(ContentType.JSON)
                .body(cordaAlterado)
                .pathParam("id", id)
                .when().put("/cordas" + "/{id}")
                .then()
                .statusCode(204);

        CordaResponseDTO response = cordaService.findById(id);
        assertThat(response.calibre(), is("jj"));
        assertThat(response.guitarra().id(), is(idParaAlterar));

    }

    static Long iidd = null;

    @Test
    @TestTransaction
    void testApagar() {

        GuitarraEletricaDTO guitarraEletrica = new GuitarraEletricaDTO("fender", 1, 3, "Hardtail");
        Long idGuitarrra = given()
                .contentType(ContentType.JSON)
                .body(guitarraEletrica)
                .when().post(GUITARRAS_ELETRICA_PATH)
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().jsonPath().getLong("id");

        CordaDTO corda = new CordaDTO("gg", idGuitarrra);
        given()
                .contentType(ContentType.JSON)
                .body(corda)
                .when().post("/cordas")
                .then()
                .statusCode(201);

        iidd =  cordaService.create(corda).id();

        given()
                .pathParam("id", iidd)
                .when().delete("/cordas" + "/{id}")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());


    }



    @Test
    void testBuscarPorGuitarra(){

        GuitarraEletricaDTO guitarraEletrica = new GuitarraEletricaDTO("fender", 1, 3, "Hardtail");
        Long idParaAlterar = given()
                .contentType(ContentType.JSON)
                .body(guitarraEletrica)
                .when()
                .post(GUITARRAS_ELETRICA_PATH)
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().jsonPath().getLong("id");

        CordaDTO corda = new CordaDTO("gg", idParaAlterar);
        given()
                .contentType(ContentType.JSON)
                .body(corda)
                .when().post("/cordas")
                .then()
                .statusCode(201)
                .body("id", notNullValue(), "calibre", is("gg"), "guitarra.id", equalTo(idParaAlterar.intValue()));

        given()
                .pathParam("id", idParaAlterar.intValue())
                .when().get("/cordas" + "/{id}")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("size()", greaterThanOrEqualTo(0));

    }

    static Long idd = null;

    @Test
    void testbuscarPorId(){

        GuitarraEletricaDTO guitarraEletrica = new GuitarraEletricaDTO("fender", 1, 3, "Hardtail");
        Long idParaAlterar = given()
                .contentType(ContentType.JSON)
                .body(guitarraEletrica)
                .when()
                .post(GUITARRAS_ELETRICA_PATH)
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().jsonPath().getLong("id");

        CordaDTO corda = new CordaDTO("gg", idParaAlterar);
        given()
                .contentType(ContentType.JSON)
                .body(corda)
                .when().post("/cordas")
                .then()
                .statusCode(201)
                .body("id", notNullValue(), "calibre", is("gg"), "guitarra.id", equalTo(idParaAlterar.intValue()));

        idd =  cordaService.create(corda).id();

        given()
                .pathParam("id", idd)
                .when().get("/cordas" + "/{id}")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("size()", greaterThanOrEqualTo(0));

    }



}
