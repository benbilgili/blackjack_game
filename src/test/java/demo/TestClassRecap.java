package demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class TestClassRecap {

    @BeforeClass
    public static void createRequestSpecification() {
        RestAssured.baseURI = "http://localhost:3002/people";
    }

    @Test                                                      // Given | When | Then - legible syntax
    public void getAllData() {
        given()
                // .header("Authorisation", "your_auth_token") // This is how authentication and other headers could be added to the requestSpec
            .when()
                .get()
            .then()
                .log().body()
                .body("$", hasSize(16)); // makes use of the Hamcrest assertions
    }


    @Test
    public void getOneItemByIndex() {
        given()
            .when()
                .get("/14")
            .then()
                .log().body()
                .body("fullName", equalTo("Hannah Anderson"))
                .body("email", equalTo("hannah.anderson@example.com"))
                .body("job", equalTo("UX/UI Designer"))
                .body("dob", equalTo("09/23/1989"));
    }


    @Test
    public void postData() {
        JSONObject postData = new JSONObject();
        String id = "31";
        postData.put("id", id);
        postData.put("fullName", "John Smith");
        postData.put("email", "js@hotmail.com");
        postData.put("job", "Teacher");
        postData.put("dob", "01/01/1967");

        given()
                .contentType(ContentType.JSON) // confirm that we are passing JSON as our body format
                .body(postData.toString())
            .when()
                .post()
            .then()
                .log().body()
                .body("id", equalTo(id));
    }

    @Test
    public void patchDataByIndex() {
        JSONObject patchData = new JSONObject();
        String name = "Ben Johnson";
        patchData.put("fullName", name);

        given()
                .contentType(ContentType.JSON)
                .body(patchData.toString())
            .when()
                .patch("/31")
            .then()
                .log().body()
                .body("fullName", equalTo(name))
                .body("job", equalTo("Teacher"));
    }

    @Test
    public void deleteOne() {
        given()
            .when()
                .delete("/31")
            .then()
                .statusCode(200); // very useful took to check status code
    }




}
