package automationApi.teste;

import automationApi.dominio.Usuario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UsuarioTeste {

    @BeforeAll
    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "https://reqres.in";
        basePath = "/api";
    }

    @Test
    public void testeMetadadosUsuario() {
        given()
                .params("page", "2")
        .when()
                .get("/users")
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
        Usuario usuario = new Usuario("Natália", "eng teste");

        given()
                //.log().all()
                .contentType(ContentType.JSON)
                .body(usuario)
        .when()
                .post("/users")
        .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", is("Natália"))
        ;
    }


}
