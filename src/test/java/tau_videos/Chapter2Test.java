package tau_videos;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.tngtech.java.junit.dataprovider.*;

@RunWith(DataProviderRunner.class)
public class Chapter2Test {

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").build();
    }

    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createResponseSpecification() {
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).build();
    }

    @DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][] {
                {"us", "90210", "Beverly Hills"},
                {"gb", "EH12", "Corstorphine"},
                {"ca", "B2R", "Waverley"},
                {"nl", "1001", "Amsterdam"}
        };
    }


    @Test
    @UseDataProvider("zipCodesAndPlaces")
    public void checkAddresses(String countryCode, String zip, String expectedResponse) {
        given().
                spec(requestSpec).
                pathParam("countryCode", countryCode).pathParam("zip", zip)
                .when().
                    get("{countryCode}/{zip}").
                then().
                    spec(responseSpec).
                and().
                    assertThat().body("places[0].'place name'", equalTo(expectedResponse));
    }

    @Test
    public void checkAddressesWithVar() {

        String placeName =

                given().
                    spec(requestSpec).
                when().
                    get("gb/EH12").
                then().
                        extract().
                        path("places[0].'place name'");

        Assert.assertEquals(placeName, "Corstorphine");
    }

    @Test
    public void checkAddressesLocationClass() {

        Location location =

                given().
                        spec(requestSpec).
                        when().
                        get("gb/EH12").
                        as(Location.class);

                Assert.assertEquals("Corstorphine", location.getPlaces().get(0).getPlaceName());
    }
}
