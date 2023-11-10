package tau_videos;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Chapter1Test {

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").build();
    }

    @Test
    public void checkEH12address() {
        given().
                spec(requestSpec).
                when().
                    get("gb/EH12").
                then().
                    assertThat().body("places[0].'place name'", equalTo("Corstorphine"));
    }

    @Test
    public void check90210state() {
        given().when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().   body("places[0].state", equalTo("California"));
    }

    @Test
    public void check90210contains() {
        given().when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().   body("places.'place name'", hasItem("Beverly Hills")); // this is checking if it is in a whole list
    }

    @Test
    public void check90210ListSize() {
        given().when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().   body("places.'place name'", hasSize(1));
    }

    @Test
    public void checkStatusCode() {
        given().when().
                    get("http://zippopotam.us/us/90210").
                then().
                    assertThat().statusCode(200);
    }

    @Test
    public void checkResponseFormat() {
        given().when().
                    get("http://zippopotam.us/us/90210").
                then().
                    assertThat().contentType(ContentType.JSON);

    }

    @Test
    public void logResponseDetails() {
        given().log().all().
                when().
                    get("http://zippopotam.us/us/90210").
                then().
                    log().body();
    }


}
