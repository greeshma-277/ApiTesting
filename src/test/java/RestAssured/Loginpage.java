import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @Test
    public void testGetPost() {
        RestAssured.given()
            .when()
            .get("https://jsonplaceholder.typicode.com/posts/1")
            .then()
            .statusCode(200)
            .body("userId", notNullValue())
            .body("id", equalTo(1))
            .body("title", notNullValue())
            .body("body", notNullValue());
    }

    @Test
    public void testPostPost() {
        String requestBody = "{\n" +
                "    \"userId\": 1,\n" +
                "    \"title\": \"foo\",\n" +
                "    \"body\": \"bar\"\n" +
                "}";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(requestBody)
            .when()
            .post("https://jsonplaceholder.typicode.com/posts");

        response.then().statusCode(201);

        // Validate the response
        response.then()
            .body("userId", equalTo(1))
            .body("title", equalTo("foo"))
            .body("body", equalTo("bar"));
    }
}
