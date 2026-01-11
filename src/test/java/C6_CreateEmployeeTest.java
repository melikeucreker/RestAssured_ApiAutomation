
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.junit.Test;

public class C6_CreateEmployeeTest {

    @Test
    public void createEmployee_withThenAssertions() {

        given()
                .baseUri("https://api.example.com")
                .contentType(ContentType.JSON)
                .body("{ \"name\": \"Melike\", \"job\": \"QA Engineer\" }")
                .when()
                .post("/employees")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("name", equalTo("Melike"))
                .body("job", equalTo("QA Engineer"));
    }
}



/*
In Rest Assured, we can validate API responses either using the fluent then() assertions for clean BDD-style tests,
 or by extracting the response into a Response object and validating it with Assert.assertEquals for more
 flexible and dynamic validations.
 */
