
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class C1_Get_Api_Request {
    /*Send a GET request to the URL https://restful-booker.herokuapp.com/booking/10 and manually
    verify that the response: Has a status code of 200 Has a
     Content-Type of application/json; charset=utf-8 Contains a Server header with
     the value Heroku Has a status line of HTTP/1.1 200 OK Has a response time of less
     than 5 seconds Note : In IntelliJ, the io.restassured library is used to perform API requests,
     and we need to create an object from the Response class. While creating a Response, the keywords
      we use have the following meanings: given: Represents the initial setup and preconditions provided
      before starting the test when: Represents
     the actions or operations performed during the test then: Represents the validation and evaluation of the response values */

    @Test
    public void getApiRequestTest() {

        // Endpoint
        String endPoint = "https://restful-booker.herokuapp.com/booking/10";

        // Send GET request
        Response response = RestAssured.given().when().get(endPoint);

        // Print response body
        response.prettyPrint();

        // Assertions
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json; charset=utf-8", response.getContentType());
        assertEquals("Heroku", response.getHeader("Server"));
        assertEquals("HTTP/1.1 200 OK", response.getStatusLine());
        assertEquals(true, response.getTime() < 5000);
    }
}
