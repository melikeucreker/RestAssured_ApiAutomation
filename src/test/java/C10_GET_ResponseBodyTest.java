

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class C10_GET_ResponseBodyTest {
    @Test
    public void test01() {
        String url = "https://jsonplaceholder.typicode.com/posts/44";
        Response response = given().when().get(url);
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .body("title", Matchers.equalTo("optio dolor molestias sit"))
                .body("id", Matchers.equalTo(44));


    }


}
