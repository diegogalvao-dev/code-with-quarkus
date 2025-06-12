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
public class EstojoResourceTeste {

    @Inject
    EstojoService estojoService;

    private String adminAuthToken;

    private static final String ESTOJO_PATH = "/estojo";
    private static final String ESTOJO_ID_PATH = ESTOJO_PATH + "/{id}";
    private static final String ESTOJO_MATERIAL_PATH = ESTOJO_PATH + "/material";
    private static final String ESTOJO_ESTILO_PATH = ESTOJO_PATH + "/estiloCase/{nomeEstiloCase}";

    @BeforeEach
    public void setUp() {
        // Obtém o token antes de cada teste
        adminAuthToken = getAdminAuthToken();
    }

    private String getAdminAuthToken() {
        AuthDTO authDTO = new AuthDTO("gerente", "123456"); // Use suas credenciais de teste "Adm"
        String token = given()
                .contentType(ContentType.JSON)
                .body(authDTO)
                .when().post("/auth") // Certifique-se que este é o path correto para o login
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().header("Authorization");

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Não foi possível obter o token de autenticação para os testes. Verifique o AuthResource e as credenciais.");
        }
        return token;
    }

    @Test
    @TestTransaction
    public void testIncluir() {

        EstojoDTO estojo = new EstojoDTO("madeira", 1);

        given()
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
                .when().get(ESTOJO_PATH)
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

    }

    @Test
    public void testBuscarPorMaterial() {

        given()
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
                .contentType(ContentType.JSON).body(estojoHardcase).when().post(ESTOJO_PATH)
                .then()
                .log().all()
                .statusCode(201)

                .extract().jsonPath().getLong("id");

        assertThat(idEstojoCriado, notNullValue());


        given().contentType(ContentType.JSON).body(estojoOutro).when().post(ESTOJO_PATH).then().statusCode(201);

        given()
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
                .contentType(ContentType.JSON).body(estojoOriginal).when().post(ESTOJO_PATH)
                .then().statusCode(201).extract().jsonPath().getLong("id");


        EstojoDTO estojoAlterado = new EstojoDTO("madeira", 2);


        given()
                .contentType(ContentType.JSON)
                .body(estojoAlterado)
                .pathParam("id", idEstojoOriginal)
                .when().put(ESTOJO_ID_PATH)
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        given()
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
                .contentType(ContentType.JSON)
                .body(estojoParaApagarDto)
                .when().post(ESTOJO_PATH)
                .then()
                .statusCode(201).extract().jsonPath().getLong("id");

        given()
                .pathParam("id", idParaApagar)
                .when().delete(ESTOJO_ID_PATH)
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

    }




}
