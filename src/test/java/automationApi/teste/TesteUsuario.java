package automationApi.teste;

import automationApi.dominio.Usuario;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TesteUsuario extends TesteBase {

    private static final String LISTA_USUARIOS_ENDPOINT = "/users";
    private static final String CRIA_USUARIO_ENDPOINT = "/users";
    private static final String MOSTRA_USUARIO_ENDPOINT = "users/{userId}";

    @Test
    public void testeMostraPaginaEspecifica() {
        given()
                .params("page", "2")
        .when()
                .get(LISTA_USUARIOS_ENDPOINT)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("page",is(2))
                .body("data",is(notNullValue()))
                /*.body("per_page",is(6))
                .body("data[0].email",is("michael.lawson@reqres.in"))
                .body("data[0].first_name",is("Michael"))*/
        ;
    }

    @Test
    public void testeCriarUsuarioComSucesso(){
        Usuario usuario = new Usuario("Natália", "eng teste", "email@gmail.com");

        given()
                .body(usuario)
        .when()
                .post(CRIA_USUARIO_ENDPOINT)
        .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", is("Natália"))
        ;
    }

   /* @Test
    public void testeTamanhoDosItensMostradosIgualAoPerPage() {
        int paginaEsperada = 2;

        int perPageEsperado = getPerPageEsperado();

        given()
                .params("page", paginaEsperada)
        .when()
                .get(LISTA_USUARIOS_ENDPOINT)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "page", is(paginaEsperada),
                        "data.size()", is(perPageEsperado),
                       "data.findAll { it.avatar.startWith('https://s3.amazonaws.com')}.size()",is(perPageEsperado)
                )
        ;
    }
*/
    @Test
    public void testeMostraUsuarioEspecifico(){
        Usuario usuario = given()
                .pathParam("userId",2)
        .when()
                .get(MOSTRA_USUARIO_ENDPOINT)
        .then()
                .statusCode(HttpStatus.SC_OK)
        .extract()
                .body().jsonPath().getObject("data", Usuario.class)
                ;
        assertThat(usuario.getEmail(), containsString("@reqres.in"));
        assertThat(usuario.getName(), is("Janet"));
        assertThat(usuario.getLastName(), is("Weaver"));

    }

    private int getPerPageEsperado() {
        int perPageEsperado = given()
                .param("page",2)
            .when()
                .get(LISTA_USUARIOS_ENDPOINT)
            .then()
                .statusCode(HttpStatus.SC_OK)
            .extract()
                .path("per_page")
            ;
        return perPageEsperado;
    }

}
