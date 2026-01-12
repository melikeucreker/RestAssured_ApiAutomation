import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C9_Basicexercise1 {
    @Test
    public void get_01() {
        //Prepare Request Body and End_point
        String url = "https://restful-booker.herokuapp.com/booking/5";
        //Prepare Expected Data
        //Send Request and Save the Response
        Response response = given().when().get(url);
        response.prettyPrint();
        //Assertion
        response.
                then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json;charset=utf-8").header("server","Heroku");


    }

}
