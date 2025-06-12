package guitarras.acme;

import guitarras.acme.dto.AuthDTO;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.given;

public class BaseTest {

    private String adminAuthToken;

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

}
