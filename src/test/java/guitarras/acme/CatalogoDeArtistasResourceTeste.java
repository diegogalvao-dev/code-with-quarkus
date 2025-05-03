package guitarras.acme;

import guitarras.acme.dto.CatalogoDeArtistasDTO;
import guitarras.acme.dto.CatalogoDeArtistasResponseDTO;
import guitarras.acme.service.CatalogoDeArtistasService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static guitarras.acme.model.EstiloMusical.HEAVYMETAL;
import static org.hamcrest.CoreMatchers.is;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class CatalogoDeArtistasResourceTeste {

    @Inject
    CatalogoDeArtistasService catalogoDeArtistasService;

    @Test
    void testBuscarTodos() {
        given()
                .when().get("/catalogoDeArtistas")
                .then()
                .statusCode(200);
    }

    @Test
    void testIncluir() {
        CatalogoDeArtistasDTO estado = new CatalogoDeArtistasDTO("hh", 2);

        given()
                .contentType(ContentType.JSON)
                .body(estado)
                .when().post("/catalogoDeArtistas")
                .then()
                .statusCode(201)
                .body(
                        "id", notNullValue(),
                        "name", is("hh")
                );
    }

    static Long id = null;

    @Test
    void testAlterar() {
        CatalogoDeArtistasDTO catalogoDeArtistasDTO = new CatalogoDeArtistasDTO("yy", 1);

        id =  catalogoDeArtistasService.create(catalogoDeArtistasDTO).getId();

        CatalogoDeArtistasDTO catalogoDeArtistasDTOalterado = new CatalogoDeArtistasDTO("tt", 3);

        given()
                .contentType(ContentType.JSON)
                .body(catalogoDeArtistasDTOalterado)
                .when().put("/catalogoDeArtistas/" + id)
                .then()
                .statusCode(200);

        CatalogoDeArtistasResponseDTO response = catalogoDeArtistasService.findById(id);
        assertThat(response.name(), is("tt"));
        assertThat(response.estiloMusical(), is(HEAVYMETAL));
    }

    @Test
    void testApagar() {
        given()
                .when().delete("/catalogoDeArtistas/" + id)
                .then()
                .statusCode(204);

        CatalogoDeArtistasResponseDTO response = catalogoDeArtistasService.findById(id);
        assertNull(response);

    }

}
