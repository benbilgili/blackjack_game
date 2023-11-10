package blackjack_game;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestClassOne {

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://www.deckofcardsapi.com/api/deck/").build();
    }

    @Test
    public void testShuffleCards() {
        given().log().all().
                spec(requestSpec).
                when().
                    get("new/shuffle/?deck_count=1").
                then().
                log().body();
    }

    @Test
    public void testView1Card() {
        given().log().all().
                spec(requestSpec).
                when().
                get("5be64cuzt4li/draw/?count=1").
                then().
                log().body();
    }

    @Test
    public void testDraw2Cards() {
        given().log().all().
                spec(requestSpec).
                when().
                get("5be64cuzt4li/draw/?count=2").
                then().
                assertThat().body("cards", hasSize(2));
    }




}
