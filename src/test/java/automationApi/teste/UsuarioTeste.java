package automationApi.teste;

import automationApi.dominio.Usuario;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class UsuarioTeste extends BaseTeste{

    private static final String LISTA_USUARIOS_ENDPOINT = "/users";
    private static final String CRIA_USUARIO_ENDPOINT = "/users";

    @Test
    public void testeListaPaginaUsuarioEspecifica() {
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
                //.log().all()
                .body(usuario)
        .when()
                .post(CRIA_USUARIO_ENDPOINT)
        .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", is("Natália"))
        ;
    }


}
