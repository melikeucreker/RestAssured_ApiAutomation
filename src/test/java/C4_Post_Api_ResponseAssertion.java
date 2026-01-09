import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.Matchers.*;



public class C4_Post_Api_ResponseAssertion {
    @Test
    public void test01(){

    /*
        When we send a POST request to
        https://jsonplaceholder.typicode.com/posts
        with the following body

            {
              "title":"API",
              "body":"Learning API is so nice",
              "userId":10
            }

        Verify that the returned Response has:
            status code 201,
            content type application/json,
            "title" in the response body is "API",
            "userId" value is less than 100,
            "body" contains the word "API".
     */

        // 1- Prepare the endpoint and request body (if required) to send the request

        String endpoint = "https://jsonplaceholder.typicode.com/posts";

        JSONObject requestBody = new JSONObject();
        requestBody.put("title","API");
        requestBody.put("body","API is so good ");
        requestBody.put("userId",10);

        // 2- If given in the question, create the expected response in our code
        // 3- Send the request and save the returned response

        Response actualResponse = RestAssured
                .given().contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .post(endpoint);

    /*
        {
            "title": "API",
            "body": "API ogrenmek ne guzel",
            "userId": 10,
            "id": 101
        }
     */

        // 4- Perform the required assertions

        // Verify:
        // status code is 201,
        // content type is application/json,
        // "title" in response body is "API",
        // "userId" value is less than 100,
        // "body" contains the word "API"

        actualResponse
                .then()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("title", Matchers.equalTo("API"))
                .body("userId",Matchers.lessThan(100))
                .body("body",Matchers.containsString("API"));

        // If you don't want to start with .body and write Matchers every time
        // while asserting response body values,
        // you can also write them like below

        actualResponse
                .then()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("title", equalTo("API"),
                        "userId", lessThan(100),
                        "body", containsString("API"));
    }






}
