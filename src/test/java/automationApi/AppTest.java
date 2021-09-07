package automationApi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class AppTest {

    @BeforeAll
    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testeMetadadosUsuario() {
        when()
                .get("https://reqres.in/api/users?page=2")
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
        given()
                //.log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Natália\", \"job\": \"eng teste\"}")
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", is("Natália"))
        ;
    }


}
