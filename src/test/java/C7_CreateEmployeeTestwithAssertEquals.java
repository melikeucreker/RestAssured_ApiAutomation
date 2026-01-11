
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;


public class C7_CreateEmployeeTestwithAssertEquals {

    @Test
    public void createEmployee_withAssertEquals() {

        Response response =
                given()
                        .baseUri("https://api.example.com")
                        .contentType(ContentType.JSON)
                        .body("{ \"name\": \"Melike\", \"job\": \"QA Engineer\" }")
                        .when()
                        .post("/employees");

        // Extract values from response
        int statusCode = response.getStatusCode();
        String name = response.path("name");
        String job = response.path("job");

        // Assertions using JUnit
        Assert.assertEquals(201, statusCode);
        Assert.assertEquals("Melike", name);
        Assert.assertEquals("QA Engineer", job);
    }
}


/*
In Rest Assured, we can validate API responses either using the fluent then() assertions for clean BDD-style tests,
 or by extracting the response into a Response object and validating it with Assert.assertEquals for more
 flexible and dynamic validations.
 */

