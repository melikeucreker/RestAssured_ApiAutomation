import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
    public class C3_Put_Api_Assertion {


        @Test
        public void test01(){



            String endpoint = "https://jsonplaceholder.typicode.com/posts/70";

            JSONObject requestBody = new JSONObject();
            requestBody.put("title","QA");
            requestBody.put("body","Hi");
            requestBody.put("userId",10);
            requestBody.put("id",70);


            Response actualResponse = RestAssured
                    .given().contentType(ContentType.JSON)
                    .when().body(requestBody.toString())
                    .put(endpoint);


            actualResponse
                    .then()
                    .assertThat()
                    .statusCode(200).contentType("application/json; charset=utf-8")
                    .header("Server","cloudflare")
                    .statusLine("HTTP/1.1 200 OK");


        }
}
