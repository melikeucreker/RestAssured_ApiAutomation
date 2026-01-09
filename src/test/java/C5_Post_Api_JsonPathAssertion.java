import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;



public class C5_Post_Api_JsonPathAssertion {
    @Test
    public void test01(){

    /*
        When we send a POST request to
        https://restful-booker.herokuapp.com/booking
        with the following body

        {
            "firstname" : "Melike",
            "lastname" : "Eker",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" : {
                "checkin" : "2023-01-10",
                "checkout" : "2023-01-20"
            },
            "additionalneeds" : "wi-fi"
        }

        Verify that the returned Response has:
            status code 200,
            content type application/json,
            and in the response body:
                "firstname" is "Melike",
                "lastname" is "Eker",
                "totalprice" is 500,
                "depositpaid" is false,
                "checkin" date is 2023-01-10,
                "checkout" date is 2023-01-20,
                "additionalneeds" is "wi-fi".
     */

        // 1- Prepare the endpoint and request body (if required) to send the request

        String endpoint = "https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = new JSONObject();

        JSONObject bookingdatesBody = new JSONObject();
        bookingdatesBody.put("checkin","2023-01-10");
        bookingdatesBody.put("checkout","2023-01-20");

        requestBody.put("firstname","Melike");
        requestBody.put("lastname","Eker");
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);
        requestBody.put("bookingdates",bookingdatesBody);
        requestBody.put("additionalneeds" , "wi-fi");

        // {
        //  "firstname":"Melike",
        //  "lastname":"Eker",
        //  "totalprice":500,
        //  "depositpaid":false,
        //  "bookingdates":{"checkin":"2023-01-10","checkout":"2023-01-20"},
        //  "additionalneeds":"wi-fi"
        // }

        // 2- If given in the question, create the expected response in our code
        // 3- Send the request and save the returned response

        Response actualResponse = RestAssured
                .given().contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .post(endpoint);

        // 4- Perform the required assertions

        // Verify:
        // status code is 200,
        // content type is application/json,
        // response body values match the expected data

        actualResponse
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("booking.firstname", equalTo("Melike"),
                        "booking.lastname", equalTo("Eker"),
                        "booking.totalprice", equalTo(500),
                        "booking.depositpaid", equalTo(false),
                        "booking.bookingdates.checkin", equalTo("2023-01-10"),
                        "booking.bookingdates.checkout", equalTo("2023-01-20"),
                        "booking.additionalneeds", equalTo("wi-fi")
                );
    }




}
