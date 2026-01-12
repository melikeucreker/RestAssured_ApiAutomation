

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class C8_JSONCRUDOPERATION {

    //  Global base URL (class-level)
    @BeforeClass
    public static void setup() {
        baseURI = "https://api.example.com";
        basePath = "/v1";
        enableLoggingOfRequestAndResponseIfValidationFails();
    }

    // ✅ Helper: Example of building a complex/nested JSON payload using JSONObject
    private JSONObject buildEmployeePayload(String name, String job) {

        // Inner/nested object: education
        JSONObject education = new JSONObject().put("degree", "Bachelor").put("university", "ABC University").put("graduationYear", 2014);

        // Another inner object: address
        JSONObject address = new JSONObject().put("city", "Istanbul").put("country", "TR").put("zip", "34000");

        // Array example: skills
        JSONArray skills = new JSONArray().put("Java").put("RestAssured").put("Selenium");

        // Main payload
        return new JSONObject().put("name", name).put("job", job).put("employeeCode", "EMP-" + UUID.randomUUID()).put("education", education)   // nested object
                .put("address", address)       // nested object
                .put("skills", skills)         // array
                .put("isActive", true);
    }

    // ✅ Helper: Example "given()" template that shows many options you can set
    private io.restassured.specification.RequestSpecification baseRequest() {
        return given()
                // Logging
                .log().all()

                // Content negotiation
                .contentType(ContentType.JSON).accept(ContentType.JSON)

                // Headers
                .header("X-Request-Source", "AQA").header("X-Correlation-Id", UUID.randomUUID().toString())

                // Cookies (example)
                .cookie("session_id", "dummy-session")

                // Params (examples)
                .queryParam("debug", true)

                // Path params will be set per test: .pathParam("id", 123)

                // Auth examples (choose what you need)
                // .auth().basic("username", "password")
                // .auth().oauth2("access_token")


                ;
    }

    // ---------------------------
    // ✅ GET
    // ---------------------------
    @Test
    public void getEmployee_shouldValidateWithJsonPath() {

        Response response = baseRequest().pathParam("id", 123).when().get("/employees/{id}");

        // Validate basic response
        assertEquals(200, response.getStatusCode());
        assertTrue("Response too slow", response.getTimeIn(TimeUnit.MILLISECONDS) < 5000);

        // JsonPath validations (expected vs actual)
        JsonPath jp = response.jsonPath();

        String actualName = jp.getString("data.name");     // example path
        String actualJob = jp.getString("data.job");

        // Example expected values (change to what your API returns)
        assertNotNull(actualName);
        assertNotNull(actualJob);
    }

    // ---------------------------
    // ✅ POST
    // ---------------------------
    @Test
    public void createEmployee_post_shouldValidateBodyWithJsonPath() {

        JSONObject payload = buildEmployeePayload("Melike", "QA Engineer");

        Response response = baseRequest().body(payload.toString()).when().post("/employees");

        assertEquals(201, response.getStatusCode());

        JsonPath jp = response.jsonPath();

        // expected vs actual (examples)
        String expectedName = payload.getString("name");
        String expectedJob = payload.getString("job");

        String actualName = jp.getString("data.name");
        String actualJob = jp.getString("data.job");

        assertEquals(expectedName, actualName);
        assertEquals(expectedJob, actualJob);

        // validate nested object example
        String expectedUniversity = payload.getJSONObject("education").getString("university");
        String actualUniversity = jp.getString("data.education.university");
        assertEquals(expectedUniversity, actualUniversity);

        // validate array example
        String firstSkillExpected = payload.getJSONArray("skills").getString(0);
        String firstSkillActual = jp.getString("data.skills[0]");
        assertEquals(firstSkillExpected, firstSkillActual);
    }

    // ---------------------------
    // ✅ PUT (full update)
    // ---------------------------
    @Test
    public void updateEmployee_put_shouldReplaceFields() {

        int employeeId = 123;

        JSONObject payload = buildEmployeePayload("Melike Updated", "Senior QA Engineer");

        Response response = baseRequest().pathParam("id", employeeId).body(payload.toString()).when().put("/employees/{id}");

        assertEquals(200, response.getStatusCode());

        JsonPath jp = response.jsonPath();
        assertEquals(payload.getString("name"), jp.getString("data.name"));
        assertEquals(payload.getString("job"), jp.getString("data.job"));
    }

    // ---------------------------
    // ✅ PATCH (partial update)
    // ---------------------------
    @Test
    public void updateEmployee_patch_shouldUpdateOnlyGivenFields() {

        int employeeId = 123;

        // Partial payload: only job + inner education.degree change example
        JSONObject educationPatch = new JSONObject().put("degree", "Master");

        JSONObject patchPayload = new JSONObject().put("job", "Lead QA Engineer").put("education", educationPatch);

        Response response = baseRequest().pathParam("id", employeeId).body(patchPayload.toString()).when().patch("/employees/{id}");

        assertEquals(200, response.getStatusCode());

        JsonPath jp = response.jsonPath();
        assertEquals("Lead QA Engineer", jp.getString("data.job"));
        assertEquals("Master", jp.getString("data.education.degree"));
    }

    // ---------------------------
    // ✅ DELETE
    // ---------------------------
    @Test
    public void deleteEmployee_shouldReturnSuccess() {

        int employeeId = 123;

        Response response = baseRequest().pathParam("id", employeeId).when().delete("/employees/{id}");

        // Depending on API it can be 200 / 204
        int code = response.getStatusCode();
        assertTrue("Unexpected status code: " + code, code == 200 || code == 204);
    }
}


