package guitarras.acme;


import guitarras.acme.dto.AuthDTO;
import guitarras.acme.dto.EstojoDTO;
import guitarras.acme.dto.EstojoResponseDTO;
import guitarras.acme.model.EstiloCase;
import guitarras.acme.service.EstojoService;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import static guitarras.acme.model.EstiloCase.HARDCASE;
import static org.hamcrest.CoreMatchers.is;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@QuarkusTest
public class EstojoResourceTeste extends BaseTest{

    @Inject
    EstojoService estojoService;

    private static final String ESTOJO_PATH = "/estojo";
    private static final String ESTOJO_ID_PATH = ESTOJO_PATH + "/{id}";
    private static final String ESTOJO_MATERIAL_PATH = ESTOJO_PATH + "/material";
    private static final String ESTOJO_ESTILO_PATH = ESTOJO_PATH + "/estiloCase/{nomeEstiloCase}";

    @Test
    @TestTransaction
    public void testIncluir() {

        EstojoDTO estojo = new EstojoDTO("madeira", 1);

        given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .contentType(ContentType.JSON)
                .body(estojo)
                .when().post(ESTOJO_PATH)
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .body("id", notNullValue(), "material", is("madeira"), "estiloCase.id", is(1), "estiloCase.nome", is("Capa Mole"));

    }


    @Test
    @TestTransaction
    public void testBuscarTodos() {

        given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .when().get(ESTOJO_PATH)
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

    }

    @Test
    public void testBuscarPorMaterial() {

        given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .queryParam("material", "madeira")
                .when().get(ESTOJO_MATERIAL_PATH)
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

    }

    @Test
    @TestTransaction
    public void testBuscarPorEstilo(){


        EstiloCase estiloBusca = EstiloCase.HARDCASE;
        EstojoDTO estojoHardcase = new EstojoDTO("material_hardcase", estiloBusca.getId());
        EstojoDTO estojoOutro = new EstojoDTO("material_outro", EstiloCase.BAG.getId());


        Long idEstojoCriado = given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .contentType(ContentType.JSON).body(estojoHardcase).when().post(ESTOJO_PATH)
                .then()
                .log().all()
                .statusCode(201)

                .extract().jsonPath().getLong("id");

        assertThat(idEstojoCriado, notNullValue());


        given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .contentType(ContentType.JSON).body(estojoOutro)
                .when().post(ESTOJO_PATH)
                .then()
                .statusCode(201);

        given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .pathParam("nomeEstiloCase", estiloBusca.name())
                .when().get(ESTOJO_ESTILO_PATH)
                .then()
                .log().all()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("size()", is(1))

                .body("[0].id", equalTo(idEstojoCriado.intValue()))
                .body("[0].material", is("material_hardcase"))

                .body("[0].estiloCase.id", is(estiloBusca.getId()))
                .body("[0].estiloCase.nome", is(estiloBusca.getNome()));
    }

    @Test
    @TestTransaction
    public void testAlterar() {

        EstojoDTO estojoOriginal = new EstojoDTO("tecido", 5);

        Long idEstojoOriginal = given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .contentType(ContentType.JSON).body(estojoOriginal).when().post(ESTOJO_PATH)
                .then().statusCode(201).extract().jsonPath().getLong("id");


        EstojoDTO estojoAlterado = new EstojoDTO("madeira", 2);


        given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .contentType(ContentType.JSON)
                .body(estojoAlterado)
                .pathParam("id", idEstojoOriginal)
                .when().put(ESTOJO_ID_PATH)
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .queryParam("material", "madeira")
                .when().get(ESTOJO_MATERIAL_PATH)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("size()", is(1))
                .body("[0].id", equalTo(idEstojoOriginal.intValue()))
                .body("[0].material", is("madeira"))
                .body("[0].estiloCase.id", is(2));
    }

    @Test
    @TestTransaction
    public void testApagar() {

        String materialParaApagar = "tecido";
        EstojoDTO estojoParaApagarDto = new EstojoDTO(materialParaApagar, 4);

        Long idParaApagar = given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .contentType(ContentType.JSON)
                .body(estojoParaApagarDto)
                .when().post(ESTOJO_PATH)
                .then()
                .statusCode(201).extract().jsonPath().getLong("id");

        given()
                .header("Authorization", "Bearer " + adminAuthToken)
                .pathParam("id", idParaApagar)
                .when().delete(ESTOJO_ID_PATH)
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

    }




}
